module edu.livraria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires org.hibernate.orm.core;
    //requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;
    requires java.sql;

    opens edu.livraria.model.entity to org.hibernate.orm.core;
    exports edu.livraria.model.entity;
    opens edu.livraria.controller to javafx.fxml;
    exports edu.livraria;
    exports edu.livraria.model.services;

    exports edu.livraria.controller to javafx.fxml;
}