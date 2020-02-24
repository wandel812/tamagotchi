package tamagotchi.controller.timer.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public abstract class UpdateTimerTask extends TimerTask {
    private Date lastTaskStartDate;

    public UpdateTimerTask() {
        this.lastTaskStartDate = Calendar.getInstance().getTime();
    }

    public Date getLastTaskStartDate() {
        return lastTaskStartDate;
    }

    public void setLastTaskStartDate(Date lastTaskStartDate) {
        this.lastTaskStartDate = lastTaskStartDate;
    }

    @Override
    public void run() {
        lastTaskStartDate = Calendar.getInstance().getTime();
    }
}
