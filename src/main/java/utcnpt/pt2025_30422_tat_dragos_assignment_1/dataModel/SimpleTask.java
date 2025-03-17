package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel;

import java.io.Serializable;

public non-sealed class SimpleTask extends Task implements Serializable {
    private  int startHour;
    private int endHour;

    public SimpleTask(int startHour, int endHour, int idTask, String nameTask) {
        super(idTask, nameTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Override
    public int estimateDuration() {
        return endHour - startHour;
    }
}
