package utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.ComplexTask;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.SimpleTask;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Utility {
    private static List<Task> tasksList;

    public Utility() {
        this.tasksList = new ArrayList<>();
    }

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

    ///we will use overloading
    public static void addTaskToList(int idTask, String statusTask, String nameTask, int startHour, int endHour) {
        Task currentTask = new SimpleTask(startHour, endHour, idTask, statusTask, nameTask);
        if (!tasksList.contains(currentTask)) {
            tasksList.add(currentTask);
        }

        throw new IllegalArgumentException("Task already exists.");
    }

    public static void addTaskToList(int idTask, String statusTask, String nameTask, List<Task> tasks) {
        Task currentTask = new ComplexTask(idTask, statusTask, nameTask);

        if (!tasksList.contains(currentTask)) {
            tasksList.add(currentTask);
            for (Task t : tasks) {
                ((ComplexTask) currentTask).addTask(t);
            }
        } else {
            throw new IllegalArgumentException("Task already exists.");
        }
    }


    public static void addEmployee(int idEmployee, String name, TasksManagement tasksManagement) {
        Employee currentEmployee = new Employee(idEmployee, name);
        if (tasksManagement.findEmployeeById(currentEmployee.getIdEmployee()) != null) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        tasksManagement.getTasksMap().put(currentEmployee, new ArrayList<>());
    }
}
