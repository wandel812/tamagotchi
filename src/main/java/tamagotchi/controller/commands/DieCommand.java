package tamagotchi.controller.commands;

import javafx.geometry.Point2D;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.animation.motion.MotionAnimation;
import tamagotchi.view.animation.sprite.SpriteAnimation;
import tamagotchi.view.animation.sprite.settings.SpriteAnimationSettings;

public class DieCommand extends Command {
    public DieCommand() {
        super();
    }

    @Override
    public void execute() {
        ModelContainer.getPetInstance().setDead(true);
        ViewContainer.getStatusLabel().setText(
                ModelContainer.getPetInstance().getCurrentOccupation().getStatusMessage());

        SpriteAnimation petTexture = ModelContainer.getPetViewInstance().getPetAnimation();
        petTexture.setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                .getSpriteAnimationSetting("die"));
        getMotionAnimation().setTransitionWithTexture(petTexture);
        getMotionAnimation().stayAnimation(
                actionEvent -> {
                    petTexture.setOtherAnimation(SpriteAnimationSettings.getAnimationSettingsInstance()
                            .getSpriteAnimationSetting("goToBetterWorld"));
                    getMotionAnimation().setTransitionWithTexture(petTexture);
                    Point2D heavenPoint = new Point2D(GAME_AREA_MARGIN_WIDTH, GAME_AREA_MARGIN_HEIGHT);
                    getMotionAnimation().moveToAnimation(ModelContainer.getPetViewInstance().getPetPosition(),
                            heavenPoint, null);
                });
    }
}
