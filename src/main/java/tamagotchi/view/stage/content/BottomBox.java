package tamagotchi.view.stage.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.data.PropertiesAccessPoint;
import tamagotchi.view.ProgressBarProperties;

public class BottomBox {
    private static VBox createHungrinessBarBox() {
        VBox hungrinessBarBox = new VBox();
        Label hungrinessLabel = new Label("hungriness");
        hungrinessLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar hungrinessBar = new ProgressBar();
        hungrinessBar.progressProperty().bind(ProgressBarProperties.hungrinessProperty());
        ViewContainer.setHungrinessBar(hungrinessBar);
        hungrinessBarBox.getChildren().addAll(hungrinessLabel, hungrinessBar);
        return hungrinessBarBox;
    }

    private static VBox createTirednessBarBox() {
        VBox tirednessBarBox = new VBox();
        Label tirednessLabel = new Label("tiredness");
        tirednessLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar tirednessBar = new ProgressBar();
        tirednessBar.progressProperty().bind(ProgressBarProperties.tirednessProperty());
        ViewContainer.setTirednessBar(tirednessBar);
        tirednessBarBox.getChildren().addAll(tirednessLabel, tirednessBar);
        return tirednessBarBox;
    }

    private static VBox createCommunicationBarBox() {
        VBox communicationBarBox = new VBox();
        Label communicationLabel = new Label("communication");
        communicationLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, 12));
        ProgressBar communicationBar = new ProgressBar();
        communicationBar.progressProperty().bind(ProgressBarProperties.communicationProperty());
        ViewContainer.setCommunicationBar(communicationBar);
        communicationBarBox.getChildren().addAll(communicationLabel, communicationBar);
        return communicationBarBox;
    }

    public static HBox createBottomBox() {
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BASELINE_LEFT);
        bottomBox.getChildren().addAll(createHungrinessBarBox(), createTirednessBarBox(), createCommunicationBarBox());
        bottomBox.setSpacing(50);
        bottomBox.setBackground(new Background(
                new BackgroundFill(
                        Color.web(PropertiesAccessPoint.applicationSettings.getProperty("boxColor")),
                        CornerRadii.EMPTY, Insets.EMPTY)));
        bottomBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null,
                new BorderWidths(1))));
        return bottomBox;
    }
}
