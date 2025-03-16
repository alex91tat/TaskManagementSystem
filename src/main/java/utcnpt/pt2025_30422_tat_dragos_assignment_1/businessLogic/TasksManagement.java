package utcnpt.pt2025_30422_tat_dragos_assignment_1.businessLogic;

import utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel.ComplexTask;
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

    public void assignTaskToEmployee(int idEmployee, Task task) {
        Employee employee = findEmployeeById(idEmployee);

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found.");
        } else if (task == null) {
            throw new IllegalArgumentException("Task not found.");
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

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        tasksMap.getOrDefault(employee, new ArrayList<>()).stream()
                .filter(task -> task.getIdTask() == idTask)
                .findFirst()
                .ifPresent(task -> {
                    task.setStatusTask(newStatus);
                     if (task instanceof ComplexTask) {
                        ((ComplexTask) task).getTasks().forEach(subTask -> subTask.setStatusTask(newStatus));
                    }
                });
    }


    public List<Task> getTasksForEmployee(int idEmployee) {
        Employee employee = findEmployeeById(idEmployee);

        if (employee == null) {
            throw new IllegalArgumentException("Employee not found");
        }

        return tasksMap.getOrDefault(employee, Collections.emptyList());
    }

    public void setTasksMap(Map<Employee, List<Task>> tasksMap) {
        this.tasksMap = tasksMap;
    }

}
