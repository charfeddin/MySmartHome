module tn.useer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires activation;
    requires mail;

    exports tn.balkis;
    exports tn.GUI;
    exports tn.controllers;

    opens tn.useer to javafx.fxml;
    opens tn.controllers to javafx.fxml;
    opens tn.entities to javafx.base; // Add this line to open tn.entities package to javafx.base
    exports tn.useer;
}
