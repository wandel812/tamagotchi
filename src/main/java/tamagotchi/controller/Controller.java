package tamagotchi.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.stage.WindowEvent;
import tamagotchi.controller.game.GameService;
import tamagotchi.controller.timer.GameTimers;
import tamagotchi.data.PropertiesAccessPoint;
import tamagotchi.controller.commands.*;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.data.saving.AppStateDto;
import tamagotchi.data.saving.SavingLoadingService;
import tamagotchi.model.pet.Occupation;
import tamagotchi.view.stage.PetChoosingStage;
import tamagotchi.view.ProgressBarProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    private static Queue<Command> commandQueue;
    private static boolean isGameRestarted = false;
    private static boolean isInCommandCycle = false;
    private static final int QUEUE_SIZE = 1;

    public static void init() {
        commandQueue = new LinkedList<>();
    }

    public static void controlCommandExecution(ActionEvent actionEvent) {
        if (isGameRestarted) {
            isGameRestarted = false;
            isInCommandCycle = false;
            return;
        }
        isInCommandCycle = true;

        Command command;
        if (ModelContainer.getPetInstance().getCurrentOccupation().equals(Occupation.DIEING)) {
            command = new DieCommand();
        } else if (!commandQueue.isEmpty()) {
            command = commandQueue.poll();
        } else if (ModelContainer.getPetInstance().getCurrentOccupation().equals(Occupation.SLEEPING)) {
            command = new SleepCommand();
        } else {
            command = new WalkAroundCommand();
        }
        command.execute();
    }

    private static boolean isPetInteractionActive() {
        return isInCommandCycle && !ModelContainer.getPetInstance().isDead() && commandQueue.size() < QUEUE_SIZE;
    }

    public static final EventHandler<ActionEvent> petButtonHandler = actionEvent -> {
        if (isPetInteractionActive()) {
            commandQueue.offer(new PetCommand());
            ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        }
    };

    public static final EventHandler<ActionEvent> feedButtonHandler = actionEvent -> {
        if (isPetInteractionActive()) {
            commandQueue.offer(new FeedCommand());
            ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        }
    };

    public static final EventHandler<ActionEvent> continueGameButtonHandler = actionEvent -> {
        Path path = Paths.get(PropertiesAccessPoint.applicationSettings.getProperty("petCharacterSaveFile"));
        if (Files.exists(path)) {
            ViewContainer.getContinueGameButton().setDisable(true);
            AppStateDto appStateDto = SavingLoadingService.loadFromFile(path.toFile());
            if (appStateDto.getPet().isDead()) {
                ViewContainer.getStatusLabel().setText("Your pet is dead =(");
            } else {
                GameService.initModelContainer(appStateDto.getPet().getName());
                GameService.changePetModel(appStateDto.getPet());
                GameService.initGameTimers(appStateDto.getGrowingUpDelayTimer());
                GameService.initProgressBars();
                ModelContainer.getPetViewInstance().setPetPosition(
                        new Point2D(appStateDto.getPetPositionX(), appStateDto.getMealPositionY()));
                ModelContainer.getMealViewInstance().setMealPosition(
                        new Point2D(appStateDto.getMealPositionX(), appStateDto.getMealPositionY()));
                ModelContainer.changeCharacterTextureRegardingAge();
                Controller.setIsGameRestarted(appStateDto.isGameRestarted());
                Controller.setIsInCommandCycle(appStateDto.isInCommandCycle());
                controlCommandExecution(null);
            }
        }
    };

    public static final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> {
        PetChoosingStage.showPetChoosingStage(ViewContainer.getRootStage());
    };

    public static final EventHandler<WindowEvent> onClosed = windowEvent -> {
        if (Controller.isInCommandCycle()) {
            Path path = Paths.get(PropertiesAccessPoint.applicationSettings.getProperty("petCharacterSaveFile"));
            try {
                Files.deleteIfExists(path);
                File file = Files.createFile(path).toFile();
                SavingLoadingService.saveToFle(
                        file,
                        new AppStateDto(
                                ModelContainer.getPetInstance(),
                                ModelContainer.getMealViewInstance().getMealPosition().getX(),
                                ModelContainer.getMealViewInstance().getMealPosition().getY(),
                                ModelContainer.getPetViewInstance().getPetPosition().getX(),
                                ModelContainer.getPetViewInstance().getPetPosition().getY(),
                                Controller.isGameRestarted(),
                                Controller.isInCommandCycle(),
                                Calendar.getInstance().getTimeInMillis()
                                        - GameTimers.getInstance().getPetGrowingUpGameTimer()
                                        .getLastTaskStartDate().getTime()
                        ));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GameService.stopGame();
    };

    public static boolean isGameRestarted() {
        return isGameRestarted;
    }

    public static void setIsGameRestarted(boolean isGameRestarted) {
        Controller.isGameRestarted = isGameRestarted;
    }

    public static boolean isInCommandCycle() {
        return isInCommandCycle;
    }

    private static void setIsInCommandCycle(boolean isInCommandCycle) {
        Controller.isInCommandCycle = isInCommandCycle;
    }
}
