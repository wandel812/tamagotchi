package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;

public class FeedCommand extends Command {
    public FeedCommand() {
        super();
    }

    @Override
    public void execute() {
        ViewContainer.getStatusLabel().setText("I'm going to eat");

        SpriteAnimationSettings spriteAnimationSettings = SpriteAnimationSettings.getAnimationSettingsInstance();
        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(spriteAnimationSettings.getSpriteAnimationSetting("walk"));
        getMotionAnimation().setTransitionWithTexture(petTexture);

        SpriteAnimation mealTexture = ModelContainer.getMealViewInstance().getMealAnimation();
        mealTexture.setOtherAnimation(
                spriteAnimationSettings.getSpriteAnimationSetting(ModelContainer.getMealInstance()
                        .getMealType().name().toLowerCase()));
        mealTexture.play();

        Point2D pointTo = ModelContainer.getMealViewInstance().getMealPosition().add(
                spriteAnimationSettings.getSpriteAnimationSetting(ModelContainer.getMealInstance()
                        .getMealType().name().toLowerCase()).getWidth(),
                0);
        getMotionAnimation().moveToAnimation(
                ModelContainer.getPetViewInstance().getPetPosition(),
                pointTo,
                actionEvent -> {
                    ModelContainer.getPetInstance().hungrinessUpdate();
                    ModelContainer.getPetViewInstance().setPetPosition(pointTo);
                    petTexture.setOtherAnimation(
                            spriteAnimationSettings.getSpriteAnimationSetting("eat"));
                    mealTexture.play();

                    getMotionAnimation().stayAnimation(actionEvent1 -> {
                        mealTexture.setOtherAnimation(
                                spriteAnimationSettings.getSpriteAnimationSetting("blank"));
                        mealTexture.play();
                        Controller.controlCommandExecution(actionEvent1);
                    });
                });
    }
}
