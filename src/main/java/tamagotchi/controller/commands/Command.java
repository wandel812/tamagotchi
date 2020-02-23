package tamagotchi.controller.commands;

import tamagotchi.PropertiesAccessPoint;

public abstract class Command {
    public static final int GAME_AREA_WIDTH;
    public static final int GAME_AREA_HEIGHT;
    public static final int GAME_AREA_MARGIN_WIDTH;
    public static final int GAME_AREA_MARGIN_HEIGHT;

    static {
        GAME_AREA_WIDTH
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("width"));
        GAME_AREA_HEIGHT
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("height"));
        GAME_AREA_MARGIN_WIDTH
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("widthMargin"));
        GAME_AREA_MARGIN_HEIGHT
                = Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("heightMargin"));
    }

    public abstract void execute();
}
