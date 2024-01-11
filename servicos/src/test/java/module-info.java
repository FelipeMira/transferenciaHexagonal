module servicos.test {

    // usa sistema e servico
    requires sistema;
    requires servicos;

    //dependencias do database
    requires spring.data.jpa;
    requires spring.boot.starter.data.jpa;
    requires spring.boot.test.autoconfigure;
    requires spring.boot.autoconfigure;
    requires spring.orm;
    requires spring.boot.test;
    requires spring.test;
    requires javax.inject;
    requires spring.beans;
    requires spring.context;
    requires spring.jdbc;
    requires spring.tx;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;

    //start nos testes
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.engine;
    requires org.junit.platform.suite.engine;
    requires org.junit.platform.suite.api;
    requires org.junit.platform.commons;
    requires spring.core;
    requires spring.boot;

    // pacote onde temos nossos testes
    exports teste.integracao;

    // abre reflexao
    opens teste.integracao;
}