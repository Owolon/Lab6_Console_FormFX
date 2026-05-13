module org.example.lab6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires jdk.jdeps;

    opens org.example.lab6 to javafx.fxml;
    exports org.example.lab6;
}