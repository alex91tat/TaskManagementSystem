module utcnpt.pt2025_30422_tat_dragos_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens utcnpt.pt2025_30422_tat_dragos_assignment_1 to javafx.fxml;
    exports utcnpt.pt2025_30422_tat_dragos_assignment_1;
    exports utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface;
    opens utcnpt.pt2025_30422_tat_dragos_assignment_1.userInterface to javafx.fxml;
}