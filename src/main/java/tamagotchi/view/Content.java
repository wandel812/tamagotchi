package tamagotchi.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tamagotchi.controller.Controller;
import tamagotchi.model.pet.Pet;
import tamagotchi.model.pet.settings.PetSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Content {
    public static Scene createPetChoosingScene(Stage currentStage) {
        List<String> petNameArray = new ArrayList<>(
                Arrays.asList(PetSettings.getPetSettingsInstance().getPetNameList()));
        ObservableList<String> pets = FXCollections.observableArrayList(petNameArray);
        ComboBox<String> petNamesComboBox = new ComboBox<>(pets);
        petNamesComboBox.setValue(pets.get(0));

        Button okButton = new Button("okay");
        okButton.setOnAction(actionEvent -> {
            Controller.initGame(petNamesComboBox.getValue());
            currentStage.close();
        });

        VBox layout = new VBox(petNamesComboBox, okButton);
        layout.setPrefSize(100, 70);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout);
    }
}
