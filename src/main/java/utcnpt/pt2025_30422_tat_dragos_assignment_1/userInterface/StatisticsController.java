package utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StatisticsController {
    @FXML
    private Button backButton;

    @FXML
    private void backButtonOnAction(ActionEvent event) {
        loadScene(backButton, "/utcnpt/pt2025_30422_tat_dragos_assignment_1/HomePageView.fxml");
    }

    private void loadScene(Button button,String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            if (loader.getLocation() == null) {
                throw new IllegalStateException("Location is not set for " + fxmlFile);
            }
            AnchorPane newScene = loader.load();

            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
