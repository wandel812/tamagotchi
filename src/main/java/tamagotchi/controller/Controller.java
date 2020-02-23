package tamagotchi.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tamagotchi.controller.commands.*;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.controller.timer.tasks.UpdatePetStateTimerTask;
import tamagotchi.model.pet.Occupation;
import tamagotchi.view.Content;
import tamagotchi.view.ProgressBarProperties;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

public class Controller {
    private static Timer timer;
    private static Queue<Command> commandQueue;
    private static boolean isGameRestarted = false;
    private static boolean isInCommandCycle = false;
    private static final int QUEUE_SIZE = 1;

    public static void init() {
        timer = new Timer();
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
        } else if (ModelContainer.getPetInstance().getCurrentOccupation().equals(Occupation.SLEEPING)) {
            command = new SleepCommand();
        } else if (!commandQueue.isEmpty()) {
            command = commandQueue.poll();
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
            ModelContainer.getPetInstance().communicationUpdate();
            ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        }
    };

    public static final EventHandler<ActionEvent> feedButtonHandler = actionEvent -> {
        if (isPetInteractionActive()) {
            commandQueue.offer(new FeedCommand());
            ModelContainer.getPetInstance().hungrinessUpdate();
            ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        }
    };

    public static final EventHandler<ActionEvent> continueGameButtonHandler = actionEvent -> {
        ViewContainer.getContinueGameButton().setDisable(true);
        /*TODO*/
    };

    public static final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> {
        ViewContainer.getContinueGameButton().setDisable(true);

        if (isInCommandCycle) {
            isGameRestarted = true;
            ViewContainer.getGameArea().getChildren().remove(
                    ModelContainer.getPetViewInstance().getPetAnimation().getTexture());
            ViewContainer.getGameArea().getChildren().remove(
                    ModelContainer.getMealViewInstance().getMealAnimation().getTexture());
        }

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ViewContainer.getRootStage());
        stage.setResizable(false);
        stage.setOnCloseRequest((WindowEvent event) -> ViewContainer.getContinueGameButton().setDisable(false));
        stage.setScene(Content.createPetChoosingScene(stage));
        stage.show();
    };

    public static void gameStop() {
        timer.cancel();
        timer.purge();
    }

    public static void initGame(String petName) {
        ModelContainer.init(petName);
        ViewContainer.getGameArea().getChildren().addAll(
                ModelContainer.getPetViewInstance().getPetAnimation().getTexture(),
                ModelContainer.getMealViewInstance().getMealAnimation().getTexture());
        ViewContainer.getStatusLabel().setText(ModelContainer.getPetInstance()
                .getCurrentOccupation().getStatusMessage());
        ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        ProgressBarProperties.setTiredness(ModelContainer.getPetInstance().getTiredness());

        timer = new Timer();
        timer.schedule(new UpdatePetStateTimerTask(), 5000, 5000);
        controlCommandExecution(null);
    }
}
