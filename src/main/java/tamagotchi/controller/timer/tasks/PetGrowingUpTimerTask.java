package tamagotchi.controller.timer.tasks;

import tamagotchi.controller.containers.ModelContainer;

public class PetGrowingUpTimerTask extends UpdateTimerTask {
    @Override
    public void run() {
        super.run();
        if (!ModelContainer.getPetInstance().isDead()) {
            ModelContainer.getPetInstance().setAdult(true);
        }
        ModelContainer.changeCharacterTextureRegardingAge();
    }
}
