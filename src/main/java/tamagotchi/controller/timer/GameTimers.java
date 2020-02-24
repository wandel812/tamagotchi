package tamagotchi.controller.timer;

import tamagotchi.controller.timer.tasks.GeneralPetStateUpdateTimerTask;
import tamagotchi.controller.timer.tasks.PetSleepingUpdateTimerTask;

import java.util.Timer;
import java.util.concurrent.TimeoutException;

public class GameTimers {
    private GameTimer generalPetStateUpdateGameTimer;
    private GameTimer petSleepUpdateGameTimer;

    private static GameTimers gameTimer;

    private GameTimers() {
        generalPetStateUpdateGameTimer = new GameTimer(new GeneralPetStateUpdateTimerTask());
        petSleepUpdateGameTimer = new GameTimer(new PetSleepingUpdateTimerTask());
    }

    public static GameTimers getInstance() {
        if (gameTimer == null) {
            gameTimer = new GameTimers();
        }
        return gameTimer;
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
}
