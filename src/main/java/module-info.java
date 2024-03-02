module tn.esprit.jihencommande {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires twilio;
    requires itextpdf;


    opens gui to javafx.fxml;
    exports gui;
}