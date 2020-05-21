module main{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens home to javafx.fxml;
    exports home;
    exports Controllers;
}