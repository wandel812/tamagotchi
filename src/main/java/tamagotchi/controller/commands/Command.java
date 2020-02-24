package tamagotchi.controller.commands;

import tamagotchi.PropertiesAccessPoint;
import tamagotchi.view.animation.motion.MotionAnimation;

public abstract class Command {
    public static final int GAME_AREA_WIDTH;
    public static final int GAME_AREA_HEIGHT;
    public static final int GAME_AREA_MARGIN_WIDTH;
    public static final int GAME_AREA_MARGIN_HEIGHT;
    private static MotionAnimation motionAnimation;

    static {
        GAME_AREA_WIDTH
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("width"));
        GAME_AREA_HEIGHT
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("height"));
        GAME_AREA_MARGIN_WIDTH
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("widthMargin"));
        GAME_AREA_MARGIN_HEIGHT
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("heightMargin"));
        motionAnimation = new MotionAnimation();
    }

    public static MotionAnimation getMotionAnimation() {
        return motionAnimation;
    }

    public static void setMotionAnimation(MotionAnimation motionAnimation) {
        Command.motionAnimation = motionAnimation;
    }

    public abstract void execute();
}
