module Marcel {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.net.http;
    requires com.google.gson;
    requires json;

    exports Marcel;
    exports Marcel.entities;
    exports Marcel.entities.person;
    exports Marcel.controllers.uicontrollers;
    exports Marcel.controllers.entitycontrollers;
    exports Marcel.controllers.fxmlcontroller;
    exports Marcel.models;
    exports Marcel.myutil;

    opens Marcel to javafx.fxml, javafx.controls;
    opens Marcel.entities to com.google.gson;
    opens Marcel.entities.person to com.google.gson;
    opens Marcel.models to com.google.gson;
    opens Marcel.controllers.uicontrollers to com.google.gson, javafx.fxml;
}
