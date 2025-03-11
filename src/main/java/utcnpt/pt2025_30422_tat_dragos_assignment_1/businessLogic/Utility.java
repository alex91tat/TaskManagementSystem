package utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utility {

    public static void displayAllEmployeesWithHighWorkDuration(TasksManagement tasksManagement) {
        tasksManagement.getEmployees().stream()
                .filter(e -> tasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee()) > 40)
                .sorted(Comparator.comparingInt(e -> tasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee())))
                .map(Employee::getName)
                .forEach(System.out::println);
    }

    public static Map<String, Map<String, Integer>> getTaskStatusCount(TasksManagement tasksManagement) {
        return tasksManagement.getEmployees().stream()
                .collect(Collectors.toMap(Employee::getName, employee -> {
                    Map<String, Integer> tasksCounting = new HashMap<>();
                            tasksCounting.put("Completed", 0);
                            tasksCounting.put("Uncompleted", 0);
                            tasksManagement.getTasksForEmployee(employee.getIdEmployee()).forEach(task -> {
                                if ("Completed".equals(task.getStatusTask())) {
                                    tasksCounting.put("Completed", tasksCounting.get("Completed") + 1);
                                } else {
                                    tasksCounting.put("Uncompleted", tasksCounting.get("Uncompleted") + 1);
                                }
                            });
                            return tasksCounting;
                        }
                ));
    }
}
