package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public non-sealed class ComplexTask extends Task implements Serializable {
    private List<Task> tasks;

    public ComplexTask(int idTask, String statusTask, String nameTask) {
        super(idTask, statusTask, nameTask);
        this.tasks = new ArrayList<Task>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        for (Task t : tasks) {
            if (t.getIdTask() == task.getIdTask()) {
                throw new IllegalArgumentException("Task already exists");
            }

            this.tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        tasks.removeIf(t -> t.getIdTask() == task.getIdTask());
    }

    @Override
    public int estimateDuration() {
        int totalDuration = 0;
        for (Task task : tasks) {
            totalDuration += task.estimateDuration();
        }

        return totalDuration;
    }
}
