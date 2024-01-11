module desktop {

    // usa conta sistema
    requires sistema;

    // ==> BUILD 3 e 4
    // usa conta serviços
    requires servicos;

    //db e auto configuration
    requires spring.data.jpa;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.orm;
    requires spring.jdbc;
    requires java.persistence;
    requires org.hibernate.orm.core;

    // usa spring
    requires spring.boot;
    requires javax.inject;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires java.sql;

    // usa javafx
    requires javafx.controls;
    requires org.yaml.snakeyaml;

    // abre telas e builds
    opens conta.javafx;
    opens conta.builds.dois;
    opens conta.builds.tresquatro;
    opens conta.javafx.tela;

    exports conta.javafx;
    exports conta.javafx.tela;
    exports conta.javafx.utils;

}