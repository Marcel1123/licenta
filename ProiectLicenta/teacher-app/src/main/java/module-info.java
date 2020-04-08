module teacher.app {
    requires java.net.http;
    requires javax.faces.api;
    requires primefaces;
    requires com.google.gson;
    requires servlet.api;
    requires java.annotation;

    exports project;
    exports screen;
    exports teacher;
    exports version;
    exports entity;
    exports session;
    exports utilitar;

    opens screen to java.net.http;
    opens project to java.net.http;
    opens teacher to java.net.http;
    opens version to java.net.http;

}