module travel.travelagency {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.logging;
    requires org.apache.logging.log4j;

    //requirements for JPA connection
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.mariadb.jdbc;

  opens travel.travelagency to javafx.fxml;
    exports travel.travelagency;
    exports travel.travelagency.controllers;
  opens travel.travelagency.controllers to javafx.fxml;
    exports travel.travelagency.database;
  opens travel.travelagency.service.consumption to javafx.base, javafx.fxml;
    exports travel.travelagency.service.consumption;
    exports travel.travelagency.service.data;
    exports travel.travelagency.entities;
  opens travel.travelagency.database to javafx.fxml, javafx.base, org.hibernate.orm.core;
  opens travel.travelagency.entities to org.hibernate.orm.core;
}