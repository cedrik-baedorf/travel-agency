module travelagency.api {
    requires jdk.httpserver;
    requires java.sql;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    exports travelagency.api;
}
