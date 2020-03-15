module teacher.app {
    requires java.net.http;
    requires javax.faces.api;
    requires com.google.gson;
    requires primefaces;
    requires servlet.api;

    exports first.screen;
    exports entity;
    exports session;
    exports utilitar;

    opens first.screen to java.net.http;
}