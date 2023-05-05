module travelagency.service {
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

  opens travelagency.service to javafx.fxml;
    exports travelagency.service;
    exports travelagency.service.controllers;
  opens travelagency.service.controllers to javafx.fxml;
    exports travelagency.service.database;
  opens travelagency.service.service.consumption to javafx.base, javafx.fxml;
    exports travelagency.service.service.consumption;
    exports travelagency.service.service.data;
    exports travelagency.service.entities;
  opens travelagency.service.database to javafx.fxml, javafx.base, org.hibernate.orm.core;
  opens travelagency.service.entities to org.hibernate.orm.core;
}