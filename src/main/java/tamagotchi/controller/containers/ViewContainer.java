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

    private static void checkInitialization(Object o) {
        if (o == null) {
            throw new RuntimeException("ViewContainer is not initialized");
        }
    }

    public static Stage getRootStage() {
        checkInitialization(rootStage);
        return rootStage;
    }

    public static BorderPane getRootPane() {
        checkInitialization(rootPane);
        return rootPane;
    }

    public static Pane getGameArea() {
        checkInitialization(gameArea);
        return gameArea;
    }

    public static Button getPetButton() {
        checkInitialization(petButton);
        return petButton;
    }

    public static Button getFeedButton() {
        checkInitialization(feedButton);
        return feedButton;
    }


    public static Button getNewGameButton() {
        checkInitialization(newGameButton);
        return newGameButton;
    }

    public static Button getContinueGameButton() {
        checkInitialization(continueGameButton);
        return continueGameButton;
    }


    public static ProgressBar getHungrinessBar() {
        checkInitialization(hungrinessBar);
        return hungrinessBar;
    }


    public static ProgressBar getTirednessBar() {
        checkInitialization(tirednessBar);
        return tirednessBar;
    }

    public static ProgressBar getCommunicationBar() {
        checkInitialization(communicationBar);
        return communicationBar;
    }

    public static Label getStatusLabel() {
        checkInitialization(statusLabel);
        return statusLabel;
    }

    public static void setRootStage(Stage rootStage) {
        ViewContainer.rootStage = rootStage;
    }

    public static void setRootPane(BorderPane rootPane) {
        ViewContainer.rootPane = rootPane;
    }

    public static void setGameArea(Pane gameArea) {
        ViewContainer.gameArea = gameArea;
    }

    public static void setPetButton(Button petButton) {
        ViewContainer.petButton = petButton;
    }

    public static void setFeedButton(Button feedButton) {
        ViewContainer.feedButton = feedButton;
    }

    public static void setNewGameButton(Button newGameButton) {
        ViewContainer.newGameButton = newGameButton;
    }

    public static void setContinueGameButton(Button continueGameButton) {
        ViewContainer.continueGameButton = continueGameButton;
    }

    public static void setHungrinessBar(ProgressBar hungrinessBar) {
        ViewContainer.hungrinessBar = hungrinessBar;
    }

    public static void setTirednessBar(ProgressBar tirednessBar) {
        ViewContainer.tirednessBar = tirednessBar;
    }

    public static void setCommunicationBar(ProgressBar communicationBar) {
        ViewContainer.communicationBar = communicationBar;
    }

    public static void setStatusLabel(Label statusLabel) {
        ViewContainer.statusLabel = statusLabel;
    }
}
