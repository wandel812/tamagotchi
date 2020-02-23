package tamagotchi.controller.containers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewContainer {
    private static Stage rootStage;
    private static BorderPane rootPane;
    private static Pane gameArea;
    private static Button petButton;
    private static Button feedButton;
    private static Button newGameButton;
    private static Button continueGameButton;
    private static ProgressBar hungrinessBar;
    private static ProgressBar tirednessBar;
    private static ProgressBar communicationBar;
    private static Label statusLabel;

    private ViewContainer() {
    }

    public static void init(Stage rootStage, BorderPane rootPane, Pane gameArea,
                     Button petButton, Button feedButton, Button newGameButton, Button continueGameButton,
                     ProgressBar hungrinessBar, ProgressBar tirednessBar,
                     ProgressBar communicationBar, Label statusLabel) {
        ViewContainer.rootStage = rootStage;
        ViewContainer.rootPane = rootPane;
        ViewContainer.gameArea = gameArea;
        ViewContainer.petButton = petButton;
        ViewContainer.feedButton = feedButton;
        ViewContainer.newGameButton = newGameButton;
        ViewContainer.continueGameButton = continueGameButton;
        ViewContainer.hungrinessBar = hungrinessBar;
        ViewContainer.tirednessBar = tirednessBar;
        ViewContainer.communicationBar = communicationBar;
        ViewContainer.statusLabel = statusLabel;
    }

    private static void checkInitialization() {
        if (rootStage == null) {
            throw new RuntimeException("ViewContainer is not initialized");
        }
    }

    public static Stage getRootStage() {
        checkInitialization();
        return rootStage;
    }

    public static BorderPane getRootPane() {
        checkInitialization();
        return rootPane;
    }

    public static Pane getGameArea() {
        checkInitialization();
        return gameArea;
    }

    public static Button getPetButton() {
        checkInitialization();
        return petButton;
    }

    public static Button getFeedButton() {
        checkInitialization();
        return feedButton;
    }


    public static Button getNewGameButton() {
        checkInitialization();
        return newGameButton;
    }

    public static Button getContinueGameButton() {
        checkInitialization();
        return continueGameButton;
    }


    public static ProgressBar getHungrinessBar() {
        checkInitialization();
        return hungrinessBar;
    }


    public static ProgressBar getTirednessBar() {
        checkInitialization();
        return tirednessBar;
    }

    public static ProgressBar getCommunicationBar() {
        checkInitialization();
        return communicationBar;
    }

    public static Label getStatusLabel() {
        checkInitialization();
        return statusLabel;
    }
}
