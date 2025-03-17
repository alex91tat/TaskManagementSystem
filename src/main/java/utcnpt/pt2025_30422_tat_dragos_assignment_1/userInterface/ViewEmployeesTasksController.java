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
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.SimpleTask;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewEmployeesTasksController {
    @FXML
    private Button backButton;
    @FXML
    private TableView<Map.Entry<Employee, List<Task>>> employeeTaskTableView;
    @FXML
    private TableColumn<Map.Entry<Employee, List<Task>>, String> employeeNameColumn;
    @FXML
    private TableColumn<Map.Entry<Employee, List<Task>>, String> taskNamesColumn;
    @FXML
    private TableColumn<Map.Entry<Employee, List<Task>>, Integer> totalDurationColumn;
    @FXML
    private TableColumn<Map.Entry<Employee, List<Task>>, Integer> employeeIdColumn;
    @FXML
    private TableView<Task> allTasksTableView;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;
    @FXML
    private TableColumn<Task, String> taskNameColumn;
    @FXML
    private TableColumn<Task, String> taskTypeColumn;
    @FXML
    private TableColumn<Task, Integer> taskDurationColumn;

    @FXML
    private void initialize() {
        // Set up the table columns for the employee-task table
        employeeIdColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getKey().getIdEmployee()).asObject()
        );

        employeeNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getKey().getName())
        );

        taskNamesColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getValue().stream()
                                .map(Task::getNameTask)
                                .collect(Collectors.joining(", "))
                )
        );

        totalDurationColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        cellData.getValue().getValue().stream()
                                .mapToInt(Task::estimateDuration)
                                .sum()
                ).asObject()
        );

        // Load data into the employee-task table
        loadEmployeeTaskData();

        // Set up the table columns for the all-tasks table
        taskIdColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getIdTask()).asObject()
        );

        taskNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNameTask())
        );

        taskTypeColumn.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            String taskType = (task instanceof SimpleTask) ? "Simple" : "Complex";
            return new SimpleStringProperty(taskType);
        });

        taskDurationColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().estimateDuration()).asObject()
        );

        // Load data into the all-tasks table
        loadAllTasksData();
    }

    private void loadEmployeeTaskData() {
        Map<Employee, List<Task>> tasksMap = TasksManagement.getTasksMap();

        // Convert the map entries to an ObservableList
        ObservableList<Map.Entry<Employee, List<Task>>> employeeTaskDataList =
                FXCollections.observableArrayList(tasksMap.entrySet());

        // Set the data in the TableView
        employeeTaskTableView.setItems(employeeTaskDataList);
    }

    private void loadAllTasksData() {
        List<Task> tasksList = Utility.getTasksList();

        // Convert the list to an ObservableList
        ObservableList<Task> observableTasks = FXCollections.observableArrayList(tasksList);

        // Set the data in the TableView
        allTasksTableView.setItems(observableTasks);
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
