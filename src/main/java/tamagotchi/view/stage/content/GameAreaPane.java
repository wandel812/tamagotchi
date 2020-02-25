package tamagotchi.view.stage.content;

import javafx.scene.layout.Pane;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.data.PropertiesAccessPoint;

public class GameAreaPane {
    public static Pane createGameArea() {
        Pane gameArea = new Pane();
        gameArea.setPrefSize(
                Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("height")),
                Integer.parseInt(PropertiesAccessPoint.applicationSettings.getProperty("width")));
        ViewContainer.setGameArea(gameArea);
        return gameArea;
    }
}
