package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.motion.MotionAnimation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;
import tamagotchi.controller.Controller;

import java.util.Random;

public class PetCommand extends Command {

    public PetCommand() {
        super();
    }

    @Override
    public void execute() {
        ViewContainer.getStatusLabel().setText("Play! Play! Play!");
        MotionAnimation motionAnimation = new MotionAnimation();
        Random rnd = new Random();
        Point2D goToPoint = new Point2D(
                rnd.nextInt(GAME_AREA_HEIGHT - 2 * GAME_AREA_MARGIN_HEIGHT) + GAME_AREA_MARGIN_HEIGHT,
                rnd.nextInt(GAME_AREA_WIDTH - 2 * GAME_AREA_MARGIN_WIDTH) + GAME_AREA_MARGIN_HEIGHT
        );

        SpriteAnimationSettings spriteAnimationSettings = SpriteAnimationSettings.getAnimationSettingsInstance();
        SpriteAnimation mealTexture = ModelContainer.getMealViewInstance().getMealAnimation();
        mealTexture.setOtherAnimation(spriteAnimationSettings.getSpriteAnimationSetting("blank"));
        mealTexture.play();

        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(spriteAnimationSettings.getSpriteAnimationSetting("walk"));
        motionAnimation.setTransitionWithTexture(petTexture);
        motionAnimation.moveToAnimation(
                ModelContainer.getPetViewInstance().getPetPosition(),
                goToPoint,
                actionEvent -> {
                    ModelContainer.getPetViewInstance().setPetPosition(goToPoint);
                    petTexture.setOtherAnimation(
                            spriteAnimationSettings.getSpriteAnimationSetting("beStill"));
                    motionAnimation.setTransitionWithTexture(petTexture);
                    motionAnimation.stayAnimation(actionEvent1 -> {
                        petTexture.setOtherAnimation(
                                spriteAnimationSettings.getSpriteAnimationSetting("jump"));
                        motionAnimation.setTransitionWithTexture(petTexture);
                        motionAnimation.stayAnimation(Controller::controlCommandExecution);
                    });
                });
    }
}
