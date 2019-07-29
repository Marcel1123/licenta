module Marcel {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    exports Marcel;
    exports Marcel.controllers.uicontrollers;
    exports Marcel.entities;
    exports Marcel.controllers.entitycontrollers;
    opens Marcel to javafx.fxml;
    exports Marcel.controllers.fxmlcontroller;
}
