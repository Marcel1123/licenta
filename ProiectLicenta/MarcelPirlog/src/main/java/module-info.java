module Marcel {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.net.http;
    requires com.google.gson;
    requires json;

    exports Marcel;
    exports Marcel.entities;
    exports Marcel.controllers.uicontrollers;
    exports Marcel.controllers.entitycontrollers;
    exports Marcel.controllers.fxmlcontroller;
    exports Marcel.models;
    opens Marcel to javafx.fxml;
    opens Marcel.entities to com.google.gson;
    opens Marcel.models to com.google.gson;
    exports Marcel.myutil;
}
