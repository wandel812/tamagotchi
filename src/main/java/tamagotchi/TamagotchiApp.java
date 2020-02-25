package tamagotchi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.stage.content.RootPane;

public class TamagotchiApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller.init();
        ViewContainer.setRootStage(primaryStage);
        primaryStage.setScene(new Scene(RootPane.createBorderPane()));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(Controller.onClosed);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
