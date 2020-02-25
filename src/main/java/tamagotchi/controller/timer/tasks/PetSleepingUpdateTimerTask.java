package tamagotchi.controller.timer.tasks;

import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.view.ProgressBarProperties;

public class PetSleepingUpdateTimerTask extends UpdateTimerTask {
    @Override
    public void run() {
        super.run();
        ModelContainer.getPetInstance().tirednessUpdate();
        ProgressBarProperties.setTiredness(ModelContainer.getPetInstance().getTiredness());
    }
}
