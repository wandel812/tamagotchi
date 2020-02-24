package tamagotchi.controller.timer.tasks;

import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.view.ProgressBarProperties;

import java.util.Calendar;

public class GeneralPetStateUpdateTimerTask extends UpdateTimerTask {

    @Override
    public void run() {
        super.run();
        ModelContainer.getPetInstance().tickUpdate();
        ProgressBarProperties.setCommunication(ModelContainer.getPetInstance().getCommunication());
        ProgressBarProperties.setHungriness(ModelContainer.getPetInstance().getHungriness());
        ProgressBarProperties.setTiredness(ModelContainer.getPetInstance().getTiredness());
    }
}
