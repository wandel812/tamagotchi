package tamagotchi.controller.timer;

import tamagotchi.controller.timer.tasks.GeneralPetStateUpdateTimerTask;
import tamagotchi.controller.timer.tasks.PetGrowingUpTimerTask;
import tamagotchi.controller.timer.tasks.PetSleepingUpdateTimerTask;

public class GameTimers {
    private GameTimer generalPetStateUpdateGameTimer;
    private GameTimer petSleepUpdateGameTimer;
    private GameTimer petGrowingUpGameTimer;

    private static GameTimers gameTimer;

    private GameTimers() {
        init();
    }

    public static GameTimers getInstance() {
        if (gameTimer == null) {
            gameTimer = new GameTimers();
        }
        return gameTimer;
    }

    public void init() {
        generalPetStateUpdateGameTimer = new GameTimer(new GeneralPetStateUpdateTimerTask());
        petSleepUpdateGameTimer = new GameTimer(new PetSleepingUpdateTimerTask());
        petGrowingUpGameTimer = new GameTimer(new PetGrowingUpTimerTask());
    }

    public void stopAllTimersIfActive() {
        getPetSleepUpdateGameTimer().stopTimerIfActive();
        getGeneralPetStateUpdateGameTimer().stopTimerIfActive();
        getPetGrowingUpGameTimer().stopTimerIfActive();
    }

    public GameTimer getGeneralPetStateUpdateGameTimer() {
        return generalPetStateUpdateGameTimer;
    }

    public void setGeneralPetStateUpdateGameTimer(GameTimer generalPetStateUpdateGameTimer) {
        this.generalPetStateUpdateGameTimer = generalPetStateUpdateGameTimer;
    }

    public GameTimer getPetSleepUpdateGameTimer() {
        return petSleepUpdateGameTimer;
    }

    public void setPetSleepUpdateGameTimer(GameTimer petSleepUpdateGameTimer) {
        this.petSleepUpdateGameTimer = petSleepUpdateGameTimer;
    }

    public GameTimer getPetGrowingUpGameTimer() {
        return petGrowingUpGameTimer;
    }

    public void setPetGrowingUpGameTimer(GameTimer petGrowingUpGameTimer) {
        this.petGrowingUpGameTimer = petGrowingUpGameTimer;
    }
}
