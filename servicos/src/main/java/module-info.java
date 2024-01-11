module servicos {
    // usa sistema
    requires sistema;

    // usa spring
    requires javax.inject;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;

    // usa persistencia e db
    requires java.sql;
    requires spring.jdbc;
    requires java.persistence;
    requires spring.data.jpa;


    // map struct e codigo estatico
    requires static lombok;
    requires java.compiler;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.orm;
    requires modelmapper;

    // pacotes para execucao do nosso servico
    exports conta.servicos.entidade;
    exports conta.servicos.interfaces;
    exports conta.servicos.repositorio;
    exports conta.servicos.utils.map;

    // abre reflexao repositorio
    opens conta.servicos.entidade;
    opens conta.servicos.interfaces;
    opens conta.servicos.repositorio;
    opens conta.servicos.config;



}