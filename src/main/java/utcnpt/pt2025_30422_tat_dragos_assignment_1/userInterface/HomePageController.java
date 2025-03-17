package utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.TasksManagement;

public class HomePageController {
    @FXML
    private Button manageButton;
    @FXML
    private Button modifyStatusButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button viewEmployeesButton;

    @FXML
    private void manageButtonOnAction(ActionEvent event) {
        loadScene(manageButton, "/utcnpt/pt2025_30422_tat_dragos_assignment_1/ManageEmployeesAndTasksView.fxml");
    }

    @FXML
    private void modifyStatusButtonOnAction(ActionEvent event) {
        loadScene(modifyStatusButton, "/utcnpt/pt2025_30422_tat_dragos_assignment_1/ModifyTaskStatusView.fxml");
    }

    @FXML
    private void statisticsButtonOnAction(ActionEvent event) {
        loadScene(statisticsButton, "/utcnpt/pt2025_30422_tat_dragos_assignment_1/StatisticsView.fxml");
    }

    @FXML
    private void viewEmployeesButtonOnAction(ActionEvent event) {
        loadScene(viewEmployeesButton, "/utcnpt/pt2025_30422_tat_dragos_assignment_1/ViewEmployeesTasksView.fxml");
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