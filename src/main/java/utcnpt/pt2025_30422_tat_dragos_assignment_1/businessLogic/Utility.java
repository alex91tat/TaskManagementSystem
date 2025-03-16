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
    public static void createTask(int idTask, String statusTask, String nameTask, int startHour, int endHour) {
        Task currentTask = new SimpleTask(startHour, endHour, idTask, statusTask, nameTask);
        if (tasksList.contains(currentTask)) {
            throw new IllegalArgumentException("Task already exists.");
        }

        tasksList.add(currentTask);
    }

//    public static void createTask(int idTask, String statusTask, String nameTask, List<String> taskNames) {
//        ComplexTask currentTask = new ComplexTask(idTask, statusTask, nameTask);
//        if (tasksList.stream().anyMatch(task -> task.getIdTask() == idTask)) {
//            throw new IllegalArgumentException("Task already exists.");
//        }
//
//        for (String taskName : taskNames) {
//            Optional<Task> foundTask = tasksList.stream()
//                    .filter(task -> task.getNameTask().equals(taskName))
//                    .findFirst();
//
//            foundTask.ifPresent(currentTask::addTask);
//        }
//
//        tasksList.add(currentTask);
//        System.out.println("Created Task: " + currentTask.getIdTask());
//    }


    public static void createTask(int idTask, String statusTask, String nameTask, List<String> tasksNames) {
        ComplexTask currentTask = new ComplexTask(idTask, statusTask, nameTask);

        if (tasksList.stream().anyMatch(task -> task.getIdTask() == idTask)) {
            throw new IllegalArgumentException("Task already exists.");
        }

        for (String taskName : tasksNames) {
            tasksList.stream()
                    .filter(task -> task.getNameTask().equals(taskName))
                    .findFirst().ifPresent(currentTask::addTask);

//            tasksList.stream()
//                    .filter(task -> task.getNameTask().equals(taskName))
//                    .findFirst()
//                    .orElse(null);

        }

        tasksList.add(currentTask);
    }


    public static void createEmployee(int idEmployee, String name, TasksManagement tasksManagement) {
        Employee currentEmployee = new Employee(idEmployee, name);
        if (tasksManagement.findEmployeeById(currentEmployee.getIdEmployee()) != null) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        tasksManagement.getTasksMap().put(currentEmployee, new ArrayList<>());
    }

    public static Task findTaskById(int idTask) {
        return tasksList.stream()
                .filter(task -> task.getIdTask() == idTask)
                .findFirst()
                .orElse(null);
    }

    public static Task findTaskByName(String taskName) {
        return tasksList.stream()
                .filter(task -> task.getNameTask().equals(taskName))
                .findFirst()
                .orElse(null);
    }

    public static void setTasksList(List<Task> tasksList) {
        Utility.tasksList = tasksList;
    }

    public static List<Task> getTasksList() {
        return tasksList;
    }
}
