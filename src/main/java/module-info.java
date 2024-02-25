module com.example.mysmarthousepidev {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.mysmarthousepidev to javafx.fxml;
    exports com.example.mysmarthousepidev;

    opens com.example.mysmarthousepidev.entities to javafx.base;
    exports com.example.mysmarthousepidev.controller;
    opens com.example.mysmarthousepidev.controller to javafx.fxml;


}