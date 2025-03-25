module com.example.clicker {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.management;

    opens com.example.clicker to javafx.fxml;
    exports com.example.clicker;
}