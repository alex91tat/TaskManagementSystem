package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel;

import java.io.Serializable;

public sealed abstract class Task implements Serializable permits SimpleTask, ComplexTask {
    private int idTask;
    private String statusTask;
    private String nameTask;

    public Task(int idTask, String statusTask, String nameTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
        this.nameTask = nameTask;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public abstract int estimateDuration();

    @Override
    public int hashCode() {
        return Integer.hashCode(idTask);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return idTask == task.idTask;
    }
}
