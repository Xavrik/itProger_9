module com.example.itproger_9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.itproger_9 to javafx.fxml;
    exports com.example.itproger_9;
}