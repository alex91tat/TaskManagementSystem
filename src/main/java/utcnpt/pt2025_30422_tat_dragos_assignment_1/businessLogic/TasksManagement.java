package utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Employee;
import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.Task;

import java.io.Serializable;
import java.util.*;

public class TasksManagement implements Serializable {
    private Map<Employee, List<Task>> tasksMap;

    public TasksManagement() {
        tasksMap = new HashMap<>();
    }

    public Map<Employee, List<Task>> getTasksMap() {
        return tasksMap;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(tasksMap.keySet());
    }

    public Employee findEmployeeById(int idEmployee) {
        return tasksMap.keySet().stream()
                .filter(employee -> employee.getIdEmployee() == idEmployee)
                .findFirst().orElse(null);
    }

    public Employee findEmployeeByName(String name) {
        return tasksMap.keySet().stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst().orElse(null);
    }

    public Task findTaskByName(String taskName) {
        return tasksMap.values().stream().flatMap(List::stream)
                .filter(task -> task.getNameTask().equals(taskName))
                .findFirst().orElse(null);
    }


    public void assignTaskToEmployee(String employeeName, String taskName) {
        Employee employee = findEmployeeByName(employeeName);
        Task task = findTaskByName(taskName);

        if (employee == null || task == null) {
            throw new IllegalArgumentException("Employee or task not found.");
        }

        tasksMap.computeIfAbsent(employee, e -> new ArrayList<>()).add(task);
    }

    public int calculateEmployeeWorkDuration(int idEmployee) {
        Employee employee = findEmployeeById(idEmployee);

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        return tasksMap.getOrDefault(employee, Collections.emptyList()).stream()
                .filter(task -> "Completed".equals(task.getStatusTask()))
                .mapToInt(Task::estimateDuration)
                .sum();
    }

    public void modifyTaskStatus(int idEmployee, int idTask, String newStatus) {
        Employee employee = findEmployeeById(idEmployee);

        if (employee != null) {
            tasksMap.get(employee).stream().filter(task -> task.getIdTask() == idTask).findFirst()
                    .ifPresent(task -> {task.setStatusTask(newStatus);});
        }

        throw new IllegalArgumentException("Employee not found");
    }

    public List<Task> getTasksForEmployee(int idEmployee) {
        Employee employee = findEmployeeById(idEmployee);

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        return tasksMap.getOrDefault(employee, Collections.emptyList());
    }

}
