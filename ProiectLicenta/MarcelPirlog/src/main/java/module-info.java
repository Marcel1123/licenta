module Marcel {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    exports Marcel;
    exports Marcel.entities;
    exports Marcel.controllers.uicontrollers;
    exports Marcel.controllers.entitycontrollers;
    exports Marcel.controllers.fxmlcontroller;
    opens Marcel to javafx.fxml;
    exports Marcel.myutil;
}
