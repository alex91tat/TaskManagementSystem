package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataAccess;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.io.*;
import java.util.List;
import java.util.Map;

public class TaskManagementSerialization {

    public static void saveData(Map<Employee, List<Task>> tasksMap, List<Task> tasksList, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(tasksMap);
            oos.writeObject(tasksList);
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void loadData(Map<Employee, List<Task>> tasksMap, List<Task> tasksList, String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Map<Employee, List<Task>> loadedTasksMap = (Map<Employee, List<Task>>) ois.readObject();
            List<Task> loadedTasksList = (List<Task>) ois.readObject();

            tasksMap.clear();
            tasksMap.putAll(loadedTasksMap);

            tasksList.clear();
            tasksList.addAll(loadedTasksList);

            System.out.println("Data loaded successfully from " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
            saveData(tasksMap, tasksList, fileName);
        } catch (EOFException | StreamCorruptedException | InvalidClassException e) {
            System.out.println("Data file is corrupted or incompatible. Creating a new one.");
            tasksMap.clear();
            tasksList.clear();
            saveData(tasksMap, tasksList, fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
