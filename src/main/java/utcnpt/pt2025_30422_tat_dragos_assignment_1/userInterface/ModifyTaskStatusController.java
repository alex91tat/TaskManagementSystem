package utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.TasksManagement;

public class ModifyTaskStatusController {
    @FXML
    private Button backButton;
    @FXML
    private Button modifyStatusButton;
    @FXML
    private TextField taskIdTextField;
    @FXML
    private TextField employeeIdTextField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private void initialize() {
        statusChoiceBox.getItems().addAll("Completed", "Uncompleted");
    }

    @FXML
    private void modifyStatusButtonOnAction(ActionEvent event) {
        String taskIdText = taskIdTextField.getText().trim();
        String employeeIdText = employeeIdTextField.getText().trim();
        String selectedStatus = statusChoiceBox.getValue();

        if (taskIdText.isEmpty() || employeeIdText.isEmpty() || selectedStatus == null) {
            System.out.println("Error: All fields must be filled!");
            return;
        }

        try {
            int taskId = Integer.parseInt(taskIdText);
            int employeeId = Integer.parseInt(employeeIdText);

            TasksManagement.modifyTaskStatus(employeeId, taskId, selectedStatus);
            System.out.println("Task status updated successfully!");

            taskIdTextField.clear();
            employeeIdTextField.clear();
            statusChoiceBox.setValue(null);
        } catch (NumberFormatException e) {
            System.out.println("Error: Task ID and Employee ID must be numbers!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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
