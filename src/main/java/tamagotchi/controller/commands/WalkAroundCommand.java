package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.motion.MotionAnimation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;
import tamagotchi.controller.Controller;
import tamagotchi.model.pet.Occupation;

import javax.swing.text.View;
import java.util.Random;

public class WalkAroundCommand extends Command {

    public WalkAroundCommand() {
        super();
    }

    @Override
    public void execute() {
        ViewContainer.getStatusLabel().setText(ModelContainer.getPetInstance()
                .getCurrentOccupation().getStatusMessage());
        Random rnd = new Random();
        MotionAnimation motionAnimation = new MotionAnimation();
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
                    Occupation petOccupation = ModelContainer.getPetInstance().getCurrentOccupation();
                    ModelContainer.getPetViewInstance().setPetPosition(goToPoint);
                    petTexture.setOtherAnimation(
                            spriteAnimationSettings.getSpriteAnimationSetting(petOccupation.getAnimationName()));
                    motionAnimation.setTransitionWithTexture(petTexture);
                    motionAnimation.stayAnimation(Controller::controlCommandExecution);
                });
    }
}
