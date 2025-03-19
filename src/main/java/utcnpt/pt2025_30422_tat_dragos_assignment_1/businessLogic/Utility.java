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

//    //initial method that displays on the screen the employees
//    public static void displayAllEmployeesWithHighWorkDuration() {
//        TasksManagement.getEmployees().stream()
//                .filter(e -> TasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee()) > 40)
//                .sorted(Comparator.comparingInt(e -> TasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee())))
//                .map(Employee::getName)
//                .forEach(System.out::println);
//    }

    //same method but returns of list of the employees so I can display them on the gui
    public static List<Employee> getEmployeesWithHighWorkDuration() {
        return TasksManagement.getEmployees().stream()
                .filter(e -> TasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee()) > 40)
                .sorted(Comparator.comparingInt(e -> TasksManagement.calculateEmployeeWorkDuration(e.getIdEmployee())))
                .collect(Collectors.toList());
    }

    public static Map<String, Map<String, Integer>> getTaskStatusCount() {
        return TasksManagement.getEmployees().stream()
                .collect(Collectors.toMap(Employee::getName, employee -> {
                    Map<String, Integer> tasksCounting = new HashMap<>();
                            tasksCounting.put("Completed", 0);
                            tasksCounting.put("Uncompleted", 0);
                            TasksManagement.getTasksForEmployee(employee.getIdEmployee()).forEach(task -> {
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
    public static void createTask(int idTask, String nameTask, int startHour, int endHour) {
        Task currentTask = new SimpleTask(startHour, endHour, idTask, nameTask);
        if (tasksList.contains(currentTask)) {
            throw new IllegalArgumentException("Task already exists.");
        }

        tasksList.add(currentTask);
    }

    public static void createTask(int idTask, String nameTask, List<Task> tasks) {
        ComplexTask currentTask = new ComplexTask(idTask, nameTask);

        if (tasksList.stream().anyMatch(task -> task.getIdTask() == idTask)) {
            throw new IllegalArgumentException("Task already exists.");
        }

        for (Task task : tasks) {
            currentTask.addTask(task);
//            tasksList.stream()
//                    .filter(task -> task.getNameTask().equals(taskName))
//                    .findFirst().ifPresent(currentTask::addTask);

//            tasksList.stream()
//                    .filter(task -> task.getNameTask().equals(taskName))
//                    .findFirst()
//                    .orElse(null);

        }

        tasksList.add(currentTask);
    }


    public static void createEmployee(int idEmployee, String name) {
        Employee currentEmployee = new Employee(idEmployee, name);
        if (TasksManagement.findEmployeeById(currentEmployee.getIdEmployee()) != null) {
            throw new IllegalArgumentException("Employee already exists.");
        }

        TasksManagement.getTasksMap().put(currentEmployee, new ArrayList<>());
    }

    public static Task findTaskById(int idTask) {
        return tasksList.stream()
                .filter(task -> task.getIdTask() == idTask)
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
