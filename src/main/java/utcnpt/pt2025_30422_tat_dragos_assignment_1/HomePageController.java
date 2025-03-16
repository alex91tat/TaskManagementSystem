package utcnpt.pt2025_30422_tat_dragos_assignment_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomePageController {
    @FXML
    private Button ok;

    @FXML
    private void okOnAction(ActionEvent event) {
        loadScene("bjbb.fxml");
    }

    private void loadScene(String fxmlFile) {
        try {
            // Use a relative path to load the FXML files
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Location is not set for " + fxmlFile);
            }
            AnchorPane newScene = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ok.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}