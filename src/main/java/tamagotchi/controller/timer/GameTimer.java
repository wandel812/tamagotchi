package tamagotchi.controller.timer;

import tamagotchi.controller.timer.tasks.PetSleepingUpdateTimerTask;
import tamagotchi.controller.timer.tasks.UpdateTimerTask;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private UpdateTimerTask timerTask;

    public GameTimer(UpdateTimerTask timerTask) {
        this.timerTask = timerTask;
    }

    public void startTimer(long delay, long period) {
        timerTask.setLastTaskStartDate(Calendar.getInstance().getTime());
        timer = new Timer();
        timer.schedule(timerTask, delay, period);
    }

    public long stopTimer() {
        long remainDurationInMillis
                = this.timerTask.getLastTaskStartDate().getTime() - Calendar.getInstance().getTime().getTime();

        timer.cancel();
        timer.purge();
        timerTask = new PetSleepingUpdateTimerTask();
        return remainDurationInMillis;

    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public TimerTask getTimerTask() {
        return timerTask;
    }

    public void setTimerTask(UpdateTimerTask timerTask) {
        this.timerTask = timerTask;
    }
}
