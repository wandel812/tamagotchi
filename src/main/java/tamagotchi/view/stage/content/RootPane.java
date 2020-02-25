package tamagotchi.view.stage.content;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.data.PropertiesAccessPoint;

public class RootPane {
    public static BorderPane createBorderPane() {
        BorderPane root = new BorderPane();
        root.setTop(TopBox.createTopBox());
        root.setCenter(GameAreaPane.createGameArea());
        root.setBottom(BottomBox.createBottomBox());
        root.setBackground(
                new Background(new BackgroundFill(
                        Color.web(PropertiesAccessPoint.applicationSettings.getProperty("backgroundColor")),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));
        ViewContainer.setRootPane(root);
        return root;
    }
}
