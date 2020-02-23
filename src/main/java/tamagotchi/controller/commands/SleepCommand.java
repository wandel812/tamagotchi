package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.motion.MotionAnimation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;

public class SleepCommand extends Command {
    public SleepCommand() {
        super();
    }

    @Override
    public void execute() {
        ModelContainer.getPetInstance().setDead(true);
        ViewContainer.getStatusLabel().setText(
                ModelContainer.getPetInstance().getCurrentOccupation().getStatusMessage());

        MotionAnimation motionAnimation = new MotionAnimation();
        SpriteAnimation mealTexture = ModelContainer.getMealViewInstance().getMealAnimation();
        mealTexture.setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                .getSpriteAnimationSetting("blank"));
        mealTexture.play();

        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                .getSpriteAnimationSetting("sleep"));
        motionAnimation.setTransitionWithTexture(petTexture);
        motionAnimation.stayAnimation(
                actionEvent -> motionAnimation.stayAnimation(Controller::controlCommandExecution));
    }
}
