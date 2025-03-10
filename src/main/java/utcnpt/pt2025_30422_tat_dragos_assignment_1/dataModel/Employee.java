package utcnpt.pt2025_30422_tat_dragos_assignment_1.dataModel;

public class Employee {
    private int idEmployee;
    private String name;

    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idEmployee);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return idEmployee == employee.idEmployee;
    }

}
