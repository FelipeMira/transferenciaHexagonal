module adaptadores.test {

    requires sistema;

    requires adaptadores;
    requires org.mockito;

    //dependencias do database
    requires spring.boot.test.autoconfigure;
    requires spring.boot.autoconfigure;
    requires spring.boot.test;
    requires spring.test;
    requires javax.inject;
    requires spring.beans;
    requires spring.context;
    requires spring.tx;
    requires spring.cloud.openfeign.core;
    requires spring.web;
    requires spring.boot.starter;
    requires modelmapper;
    requires spring.data.redis;
    requires spring.rabbit;
    requires spring.amqp;
    requires com.google.gson;
    requires com.fasterxml.jackson.core;

    //start nos testes
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.engine;
    requires org.junit.platform.suite.engine;
    requires org.junit.platform.suite.api;
    requires org.junit.platform.commons;
    requires spring.core;
    requires spring.boot;

    exports teste;

    opens teste;

    exports teste.config;
    opens teste.config;
}