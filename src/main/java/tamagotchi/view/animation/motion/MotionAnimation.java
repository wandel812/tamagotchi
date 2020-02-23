package tamagotchi.view.animation.motion;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import tamagotchi.view.animation.TransitionWithTexture;
import tamagotchi.view.animation.motion.settings.MotionAnimationSetting;
import tamagotchi.view.animation.motion.settings.MotionAnimationSettings;

public class MotionAnimation {
    private TransitionWithTexture spriteAnimation;

    public MotionAnimation() {
    }

    public void moveToAnimation(Point2D pointFrom, Point2D pointTo, EventHandler<ActionEvent> onFinishedHandler) {
        TranslateTransition transition = new TranslateTransition();
        MotionAnimationSetting setting
                = MotionAnimationSettings.getAnimationSettingsInstance().getMotionAnimationSetting("moveTo");
        transition.setDuration(Duration.millis(setting.getDurationInMillis()));
        transition.setFromX(pointFrom.getX());
        transition.setFromY(pointFrom.getY());
        transition.setToX(pointTo.getX());
        transition.setToY(pointTo.getY());
        transition.setNode(spriteAnimation.getTexture());
        spriteAnimation.setCycleCount(
                (int) Math.round(spriteAnimation.getCycleDuration().toMillis() / transition.getDuration().toMillis())
        );
        if (onFinishedHandler != null) {
            transition.setOnFinished(onFinishedHandler);
        }
        spriteAnimation.play();
        transition.play();
    }

    public void stayAnimation(EventHandler<ActionEvent> onFinishedHandler) {
        PauseTransition transition = new PauseTransition();
        MotionAnimationSetting setting
                = MotionAnimationSettings.getAnimationSettingsInstance().getMotionAnimationSetting("stay");
        transition.setDuration(Duration.millis(setting.getDurationInMillis()));
        spriteAnimation.setCycleCount(
                (int) Math.round(spriteAnimation.getCycleDuration().toMillis() / transition.getDuration().toMillis())
        );
        if (onFinishedHandler != null) {
            transition.setOnFinished(onFinishedHandler);
        }
        spriteAnimation.play();
        transition.play();
    }

    public TransitionWithTexture getTransitionWithTexture() {
        return spriteAnimation;
    }

    public void setTransitionWithTexture(TransitionWithTexture texture) {
        this.spriteAnimation = texture;
    }
}
