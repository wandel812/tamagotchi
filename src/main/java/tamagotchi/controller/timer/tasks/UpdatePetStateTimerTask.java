package tamagotchi.controller.timer.tasks;

import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.view.ProgressBarProperties;

import java.util.TimerTask;

public class UpdatePetStateTimerTask extends TimerTask {
    @Override
    public void run() {
        ModelContainer.getPetInstance().tickUpdate();
        ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        ProgressBarProperties.setTiredness(ModelContainer.getPetInstance().getTiredness());
    }
}
