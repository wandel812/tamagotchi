package tamagotchi.view.stage.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.data.PropertiesAccessPoint;

import javax.swing.text.View;

public class TopBox {
    private static HBox createLabelBox() {
        HBox labelBox = new HBox();
        Label petStatusLabel = new Label(".......");
        petStatusLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
        labelBox.getChildren().addAll(petStatusLabel);
        labelBox.setAlignment(Pos.CENTER);
        ViewContainer.setStatusLabel(petStatusLabel);
        return labelBox;
    }

    private static HBox createPetBehaviorButtonBox() {
        HBox petBehaviorButtonBox = new HBox();
        Button petButton = new Button("pet");
        petButton.setOnAction(Controller.petButtonHandler);
        ViewContainer.setPetButton(petButton);
        Button feedButton = new Button("feed");
        feedButton.setOnAction(Controller.feedButtonHandler);
        ViewContainer.setFeedButton(feedButton);
        petBehaviorButtonBox.getChildren().addAll(petButton, feedButton);
        petBehaviorButtonBox.setSpacing(1);
        return petBehaviorButtonBox;
    }

    private static HBox createGameCtrlButtonBox() {
        HBox gameCtrlButtonBox = new HBox();
        Button newGameButton = new Button("new game");
        newGameButton.setOnAction(Controller.newGameButtonHandler);
        ViewContainer.setNewGameButton(newGameButton);
        Button continueGameButton = new Button("continue");
        continueGameButton.setOnAction(Controller.continueGameButtonHandler);
        ViewContainer.setContinueGameButton(continueGameButton);
        gameCtrlButtonBox.getChildren().addAll(newGameButton, continueGameButton);
        gameCtrlButtonBox.setSpacing(1);
        return gameCtrlButtonBox;
    }

    public static HBox createTopBox() {
        HBox topBox = new HBox();
        topBox.getChildren().addAll( createGameCtrlButtonBox(), createPetBehaviorButtonBox(), createLabelBox());
        topBox.setSpacing(16);
        topBox.setBackground(new Background(
                new BackgroundFill(Color.web(PropertiesAccessPoint.applicationSettings.getProperty("boxColor")),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        topBox.setPadding(new Insets(0,0,5,0));
        topBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null,
                new BorderWidths(1))));
        return topBox;
    }
}
