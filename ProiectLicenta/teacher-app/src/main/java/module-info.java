module teacher.app {
    requires java.net.http;
    requires javax.faces.api;
    requires primefaces;
    requires com.google.gson;
    requires servlet.api;
    requires java.annotation;

    exports first.screen;
    exports entity;
    exports session;
    exports utilitar;

    opens first.screen to java.net.http;
}