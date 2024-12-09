module edu.Livraria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires lombok; // static não é necessário se configurado corretamente no build
    requires java.sql;

    // Abrindo pacotes para frameworks e bibliotecas específicas
    opens edu.Livraria.model.entity to org.hibernate.orm.core;
    opens edu.Livraria.controller to javafx.fxml;

    // Exportando pacotes do projeto
    exports edu.Livraria.model.entity;
    exports edu.Livraria;
    exports edu.Livraria.model.services;
    exports edu.Livraria.controller;
}
