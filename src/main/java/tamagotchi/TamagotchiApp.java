package tamagotchi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.view.ProgressBarProperties;

public class TamagotchiApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane gameArea = new Pane();
        gameArea.setPrefSize(500, 400);
/*
        Pet pet = Pet.getInstance();
        pet.setSpriteAnimation();*/

        HBox labelBox = new HBox();
        Label petStatusLabel = new Label("petStatusLabel");
        petStatusLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 14));
        labelBox.getChildren().addAll(petStatusLabel);
        labelBox.setAlignment(Pos.CENTER);
        //labelBox.setPadding(new Insets(0, 0, 0, 30));

        HBox petBehaviorButtonBox = new HBox();
        Button petButton = new Button("pet");
        petButton.setOnAction(Controller.petButtonHandler);
        Button feedButton = new Button("feed");
        feedButton.setOnAction(Controller.feedButtonHandler);
        petBehaviorButtonBox.getChildren().addAll(petButton, feedButton);
        petBehaviorButtonBox.setSpacing(1);

        HBox gameCtrlButtonBox = new HBox();
        Button newGameButton = new Button("new game");
        newGameButton.setOnAction(Controller.newGameButtonHandler);
        Button continueGameButton = new Button("continue");
        //continueGame.setOnAction(Controller.continueGameButtonHandler);
        gameCtrlButtonBox.getChildren().addAll(newGameButton, continueGameButton);
        gameCtrlButtonBox.setSpacing(1);

        HBox topBox = new HBox();
        topBox.getChildren().addAll(gameCtrlButtonBox, petBehaviorButtonBox, labelBox);
        topBox.setSpacing(16);
        topBox.setBackground(
                new Background(new BackgroundFill(Color.web("#818a83"), CornerRadii.EMPTY, Insets.EMPTY))
        );
        topBox.setPadding(new Insets(0,0,5,0));
        topBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null,
                new BorderWidths(1))));

        VBox hungrinessBarArea = new VBox();
        Label hungrinessLabel = new Label("hungriness");
        hungrinessLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar hungrinessBar = new ProgressBar();
        hungrinessBar.progressProperty().bind(ProgressBarProperties.hungrinessProperty());
        hungrinessBarArea.getChildren().addAll(hungrinessLabel, hungrinessBar);

        VBox tirednessBarArea = new VBox();
        Label tirednessLabel = new Label("tiredness");
        tirednessLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar tirednessBar = new ProgressBar();
        tirednessBar.progressProperty().bind(ProgressBarProperties.tirednessProperty());
        tirednessBarArea.getChildren().addAll(tirednessLabel, tirednessBar);

        VBox communicationBarArea = new VBox();
        Label communicationLabel = new Label("communication");
        communicationLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar communicationBar = new ProgressBar();
        communicationBar.progressProperty().bind(ProgressBarProperties.communicationProperty());
        communicationBarArea.getChildren().addAll(communicationLabel, communicationBar);

        HBox barArea = new HBox();
        barArea.setAlignment(Pos.BASELINE_LEFT);
        barArea.setSpacing(50);
        barArea.getChildren().addAll(hungrinessBarArea, tirednessBarArea, communicationBarArea);
        barArea.setBackground(
                new Background(new BackgroundFill(Color.web("#818a83"), CornerRadii.EMPTY, Insets.EMPTY))
        );
        barArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null,
                new BorderWidths(1))));



        BorderPane root = new BorderPane();

        root.setTop(topBox);
        root.setCenter(gameArea);
        root.setBottom(barArea);
        root.setBackground(
                new Background(new BackgroundFill(Color.web("#306040"), CornerRadii.EMPTY, Insets.EMPTY))
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Controller.init();
        ViewContainer.init(
                primaryStage, root, gameArea,
                petButton, feedButton, newGameButton, continueGameButton,
                hungrinessBar, tirednessBar, communicationBar, petStatusLabel);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
