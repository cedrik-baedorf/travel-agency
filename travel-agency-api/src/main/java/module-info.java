module travelagency.api {
    requires jdk.httpserver;
    requires java.sql;
    requires com.google.gson;
    requires travelagency.service;

    exports travelagency.api;
}
