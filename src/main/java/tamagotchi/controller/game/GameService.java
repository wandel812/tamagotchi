package tamagotchi.controller.game;

import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.controller.timer.GameTimers;
import tamagotchi.data.PropertiesAccessPoint;
import tamagotchi.model.pet.Pet;
import tamagotchi.view.ProgressBarProperties;

public class GameService {
    public static void stopGame() {
        GameTimers.getInstance().stopAllTimersIfActive();
    }

    public static void startGame(String petName) {
        initGameTimers();
        initModelContainer(petName);
        initProgressBars();
        Controller.controlCommandExecution(null);
    }

    public static void initGameTimers(long growingUpDelay) {
        if (Controller.isGameRestarted()) {
            GameTimers.getInstance().init();
        }
        GameTimers.getInstance().getGeneralPetStateUpdateGameTimer().startTimer(
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickSpan")),
                Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("tickSpan"))
        );
        GameTimers.getInstance().getPetGrowingUpGameTimer().startTimer(growingUpDelay);
    }

    public static void initGameTimers() {
        initGameTimers(Integer.parseInt(PropertiesAccessPoint.petBehaviorSettings.getProperty("growingUpSpan")));
    }

    public static void initModelContainer(String petName) {
        ModelContainer.init(petName);
        ViewContainer.getGameArea().getChildren().addAll(
                ModelContainer.getPetViewInstance().getPetAnimation().getTexture(),
                ModelContainer.getMealViewInstance().getMealAnimation().getTexture());
        ViewContainer.getStatusLabel().setText(ModelContainer.getPetInstance()
                .getCurrentOccupation().getStatusMessage());
    }

    public static void initProgressBars() {
        ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        ProgressBarProperties.setTiredness(ModelContainer.getPetInstance().getTiredness());
    }

    public static void changePetModel(Pet pet) {
        ModelContainer.getPetInstance().setName(pet.getName());
        ModelContainer.getPetInstance().setCommunication(pet.getCommunication());
        ModelContainer.getPetInstance().setTiredness(pet.getTiredness());
        ModelContainer.getPetInstance().setHungriness(pet.getHungriness());
        ModelContainer.getPetInstance().setAdult(pet.isAdult());
        ModelContainer.getPetInstance().setDead(pet.isDead());
        ModelContainer.getPetInstance().setSleeping(pet.isSleeping());
    }
}
