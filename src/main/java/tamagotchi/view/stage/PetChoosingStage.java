package tamagotchi.view.stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tamagotchi.controller.Controller;
import tamagotchi.controller.containers.ModelContainer;
import tamagotchi.controller.containers.ViewContainer;
import tamagotchi.controller.game.GameService;
import tamagotchi.model.pet.settings.PetSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetChoosingStage {
    private static Scene createPetChoosingScene(Stage currentStage) {
        List<String> petNameArray = new ArrayList<>(
                Arrays.asList(PetSettings.getPetSettingsInstance().getPetNameList()));
        ObservableList<String> pets = FXCollections.observableArrayList(petNameArray);
        ComboBox<String> petNamesComboBox = new ComboBox<>(pets);
        petNamesComboBox.setValue(pets.get(0));

        Button okButton = new Button("okay");
        okButton.setOnAction(actionEvent -> {
            ViewContainer.getContinueGameButton().setDisable(true);
            if (Controller.isInCommandCycle()) {
                Controller.setIsGameRestarted(true);
                ViewContainer.getGameArea().getChildren().remove(
                        ModelContainer.getPetViewInstance().getPetAnimation().getTexture());
                ViewContainer.getGameArea().getChildren().remove(
                        ModelContainer.getMealViewInstance().getMealAnimation().getTexture());
            }
            GameService.stopGame();
            GameService.startGame(petNamesComboBox.getValue());
            currentStage.close();
        });

        VBox layout = new VBox(petNamesComboBox, okButton);
        layout.setPrefSize(100, 70);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout);
    }

    public static void showPetChoosingStage(Stage owner) {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setResizable(false);
        stage.setScene(PetChoosingStage.createPetChoosingScene(stage));
        stage.show();
    }
}
