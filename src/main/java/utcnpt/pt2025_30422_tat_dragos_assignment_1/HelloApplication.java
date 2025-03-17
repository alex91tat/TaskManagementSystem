package utcnpt.pt2025_30422_tat_dragos_assignment_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.TasksManagement;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic.Utility;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataAccess.TaskManagementSerialization;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface.HomePageController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TasksManagement tasksManagement = new TasksManagement();
        Utility utility = new Utility();

        String fileName = "data.ser";
        Map<Employee, List<Task>> tasksMap = new HashMap<>();
        List<Task> tasksList = new ArrayList<>();

        TaskManagementSerialization.loadData(tasksMap, tasksList, fileName);
        TasksManagement.setTasksMap(tasksMap);
        Utility.setTasksList(tasksList);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Task Management System");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            System.out.println("Saving data before closing...");
            TaskManagementSerialization.saveData(tasksMap, tasksList, fileName);
            System.out.println("Data saved successfully.");
        });
    }


    public static void main(String[] args) {
        launch();
    }
}