package utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.TasksManagement;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.Utility;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;

import java.util.List;
import java.util.Map;

public class StatisticsController {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Employee> employeeStatsTableView;
    @FXML
    private TableColumn<Employee, String> employeeNameColumn;
    @FXML
    private TableColumn<Employee, Integer> workDurationColumn;
    @FXML
    private TableView<Map.Entry<String, Map<String, Integer>>> taskStatusTableView;
    @FXML
    private TableColumn<Map.Entry<String, Map<String, Integer>>, String> employeeNameStatusColumn;
    @FXML
    private TableColumn<Map.Entry<String, Map<String, Integer>>, Integer> completedTasksColumn;
    @FXML
    private TableColumn<Map.Entry<String, Map<String, Integer>>, Integer> uncompletedTasksColumn;

    @FXML
    private void initialize() {
        employeeNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName())
        );

        workDurationColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(TasksManagement.calculateEmployeeWorkDuration(cellData.getValue().getIdEmployee())).asObject()
        );

        loadEmployeeStatsData();

        employeeNameStatusColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKey())
        );

        completedTasksColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getValue().get("Completed")).asObject()
        );

        uncompletedTasksColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getValue().get("Uncompleted")).asObject()
        );

        loadTaskStatusData();
    }

    private void loadEmployeeStatsData() {
        List<Employee> employees = Utility.getEmployeesWithHighWorkDuration();
        ObservableList<Employee> observableEmployees = FXCollections.observableArrayList(employees);

        employeeStatsTableView.setItems(observableEmployees);
    }

    private void loadTaskStatusData() {
        Map<String, Map<String, Integer>> taskStatusCount = Utility.getTaskStatusCount();

        ObservableList<Map.Entry<String, Map<String, Integer>>> observableTaskStatus =
                FXCollections.observableArrayList(taskStatusCount.entrySet());

        taskStatusTableView.setItems(observableTaskStatus);
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
