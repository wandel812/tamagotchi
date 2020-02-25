package tamagotchi.controller.timer;

import tamagotchi.controller.timer.tasks.PetSleepingUpdateTimerTask;
import tamagotchi.controller.timer.tasks.UpdateTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class GameTimer {
    private boolean isActive = false;
    private Timer timer;
    private UpdateTimerTask timerTask;

    public GameTimer(UpdateTimerTask timerTask) {
        this.timerTask = timerTask;
    }

    private void init() {
        isActive = true;
        timer = new Timer();
        timerTask.setLastTaskStartDate(Calendar.getInstance().getTime());
    }

    public void startTimer(long delay, long period) {
        init();
        timer.schedule(timerTask, delay, period);
    }

    public void startTimer(long delay) {
        init();
        timer.schedule(timerTask, delay);
    }

    public void stopTimer() {
        timer.cancel();
        timer.purge();
        isActive = false;
        timerTask = new PetSleepingUpdateTimerTask();

    }

    public void stopTimerIfActive() {
        if (isActive) {
            stopTimer();
        }
    }

    public Date getLastTaskStartDate() {
        return timerTask.getLastTaskStartDate();
    }
}
