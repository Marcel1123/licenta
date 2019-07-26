module Marcel {
    requires javafx.controls;
    requires javafx.fxml;

    opens Marcel to javafx.fxml;
    exports Marcel;
}