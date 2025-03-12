package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataAccess;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.io.*;
import java.util.List;
import java.util.Map;

public class TaskManagementSerialization {
    public static void saveTasksManagement(Map<Employee, List<Task>> tasksMap, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasksMap);
            System.out.println("TasksManagement data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving TasksManagement data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Map<Employee, List<Task>> loadTasksManagement(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Employee, List<Task>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading TasksManagement data: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
