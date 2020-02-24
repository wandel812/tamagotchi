package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.motion.MotionAnimation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;
import tamagotchi.controller.Controller;

public class FeedCommand extends Command {
    public FeedCommand() {
        super();
    }

    @Override
    public void execute() {
        ViewContainer.getStatusLabel().setText("I'm going to eat");
        MotionAnimation motionAnimation = new MotionAnimation();

        SpriteAnimationSettings spriteAnimationSettings = SpriteAnimationSettings.getAnimationSettingsInstance();
        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(spriteAnimationSettings.getSpriteAnimationSetting("walk"));
        motionAnimation.setTransitionWithTexture(petTexture);

        SpriteAnimation mealTexture = ModelContainer.getMealViewInstance().getMealAnimation();
        mealTexture.setOtherAnimation(
                spriteAnimationSettings.getSpriteAnimationSetting(ModelContainer.getMealInstance()
                        .getMealType().name().toLowerCase()));
        mealTexture.play();

        Point2D pointTo = ModelContainer.getMealViewInstance().getMealPosition().add(
                spriteAnimationSettings.getSpriteAnimationSetting(ModelContainer.getMealInstance()
                        .getMealType().name().toLowerCase()).getWidth(),
                0);
        motionAnimation.moveToAnimation(
                ModelContainer.getPetViewInstance().getPetPosition(),
                pointTo,
                actionEvent -> {
                    ModelContainer.getPetViewInstance().setPetPosition(pointTo);
                    petTexture.setOtherAnimation(
                            spriteAnimationSettings.getSpriteAnimationSetting("eat"));
                    mealTexture.play();

                    motionAnimation.stayAnimation(actionEvent1 -> {
                        mealTexture.setOtherAnimation(
                                spriteAnimationSettings.getSpriteAnimationSetting("blank"));
                        mealTexture.play();
                        Controller.controlCommandExecution(actionEvent1);
                    });
                });
    }
}
