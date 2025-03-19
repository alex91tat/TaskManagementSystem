package utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.TasksManagement;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.Utility;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployeesAndTasksController {
    @FXML
    private Button backButton;
    @FXML
    private Button addEmployeeButton;
    @FXML
    private TextField nameEmployeeTextField;
    @FXML
    private TextField idEmployeeTextField;
    @FXML
    private TextField idTaskTextField;
    @FXML
    private TextField nameTaskTextField;
    @FXML
    private ChoiceBox<String> taskTypeBox;
    @FXML
    private TextField startHourTextField;
    @FXML
    private TextField endHourTextField;
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private Button addTaskButton;
    @FXML
    private TableColumn<Task, Integer> idColumn;
    @FXML
    private TableColumn<Task, String> nameColumn;
    @FXML
    private Button assignTaskButton;
    @FXML
    private TextField idTaskAssigTextField;
    @FXML
    private TextField idEmployeeAssigTextField;

    @FXML
    public void assignTaskButtonOnAction(ActionEvent event) {
        String idEmployeeText = idEmployeeAssigTextField.getText().trim();
        String idTaskText = idTaskAssigTextField.getText().trim();

        if (idEmployeeText.isEmpty() || idTaskText.isEmpty()) {
            System.out.println("Error: Both Employee ID and Task ID must be filled!");
            return;
        }

        try {
            int idEmployee = Integer.parseInt(idEmployeeText);
            int idTask = Integer.parseInt(idTaskText);

            Task taskToAssign = Utility.findTaskById(idTask);
            if (taskToAssign == null) {
                System.out.println("Error: Task with ID " + idTask + " not found.");
                return;
            }

            TasksManagement.assignTaskToEmployee(idEmployee, taskToAssign);
            System.out.println("Task " + idTask + " assigned to Employee " + idEmployee);

            idEmployeeAssigTextField.clear();
            idTaskAssigTextField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Error: Employee ID and Task ID must be numbers!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            if (task != null) {
                return new SimpleIntegerProperty(task.getIdTask()).asObject();
            } else {
                return new SimpleIntegerProperty(0).asObject();
            }
        });

        nameColumn.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            if (task != null) {
                return new SimpleStringProperty(task.getNameTask());
            } else {
                return new SimpleStringProperty("");
            }
        });

        loadTasksIntoTable();

        taskTypeBox.getItems().addAll("Simple", "Complex");

        taskTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("Simple".equals(newValue)) {
                taskTableView.setDisable(true);
                taskTableView.getSelectionModel().clearSelection();
                startHourTextField.setDisable(false);
                endHourTextField.setDisable(false);
            } else if ("Complex".equals(newValue)) {
                taskTableView.setDisable(false);
                taskTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                startHourTextField.setDisable(true);
                endHourTextField.setDisable(true);
                startHourTextField.clear();
                endHourTextField.clear();
            }
        });
    }

    @FXML
    public void addTaskButtonOnAction(ActionEvent event) {
        String tasksName = nameTaskTextField.getText().trim();
        String taskIdText = idTaskTextField.getText().trim();
        String taskType = taskTypeBox.getValue();

        if (tasksName.isEmpty() || taskIdText.isEmpty() || taskType == null) {
            System.out.println("Error: All fields must be filled!");
            return;
        }

        try {
            int taskId = Integer.parseInt(taskIdText);

            if (tasksName.isEmpty()) {
                tasksName = "Unnamed Task";
            }

            if ("Simple".equals(taskType)) {
                addSimpleTask(taskId, tasksName);
            } else if ("Complex".equals(taskType)) {
                addComplexTask(taskId, tasksName);
            }

            nameTaskTextField.clear();
            idTaskTextField.clear();
            startHourTextField.clear();
            endHourTextField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Error: Task ID must be a number!");
        }
    }

    @FXML
    private void loadTasksIntoTable() {
        List<Task> taskList = Utility.getTasksList();

        if (taskList == null) {
            taskList = new ArrayList<>();
        }

        ObservableList<Task> observableTasks = FXCollections.observableArrayList(taskList);
        taskTableView.setItems(observableTasks);
    }

    private void addSimpleTask(int taskId, String taskName) {
        String startHourText = startHourTextField.getText().trim();
        String endHourText = endHourTextField.getText().trim();

        if (startHourText.isEmpty() || endHourText.isEmpty()) {
            System.out.println("Error: Start and End hours must be provided for a Simple task!");
            return;
        }

        try {
            int startHour = Integer.parseInt(startHourText);
            int endHour = Integer.parseInt(endHourText);

            if (startHour >= endHour) {
                System.out.println("Error: Start hour must be less than End hour!");
                return;
            }

            Utility.createTask(taskId, taskName, startHour, endHour);
            System.out.println("Simple Task added: " + taskName);
            loadTasksIntoTable();
        } catch (NumberFormatException e) {
            System.out.println("Error: Start and End hours must be numbers!");
        }
    }

    private void addComplexTask(int taskId, String taskName) {
        ObservableList<Task> selectedTasks = taskTableView.getSelectionModel().getSelectedItems();

        if (selectedTasks.isEmpty()) {
            System.out.println("Error: Select at least one sub-task for a Complex task!");
            return;
        }

        List<Task> subTasks = List.copyOf(selectedTasks);
        Utility.createTask(taskId, taskName, subTasks);
        System.out.println("Complex Task added: " + taskName + " with " + subTasks.size() + " sub-tasks.");
        loadTasksIntoTable();
    }

    @FXML
    public void addEmployeeButtonOnAction(ActionEvent event) {
        String name = nameEmployeeTextField.getText().trim();
        String id = idEmployeeTextField.getText().trim();

        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Error: Both fields must be filled!");
            return;
        }

        try {
            int employeeId = Integer.parseInt(id);
            Utility.createEmployee(employeeId, name);
            System.out.println("Employee added: " + name + " (ID: " + employeeId + ")");
            nameEmployeeTextField.clear();
            idEmployeeTextField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Error: ID must be a number!");
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
