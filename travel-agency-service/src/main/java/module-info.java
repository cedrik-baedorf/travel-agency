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

  opens travelagency.service;
    exports travelagency.service;

  opens travelagency.service.controllers;
    exports travelagency.service.controllers;

  opens travelagency.service.service.consumption;
    exports travelagency.service.service.consumption;

  opens travelagency.service.service.data;
    exports travelagency.service.service.data;

  opens travelagency.service.database;
    exports travelagency.service.database;

  opens travelagency.service.entities;
    exports travelagency.service.entities;
}