module conta.api {

    requires static lombok;

    requires java.validation;
    requires javax.interceptor.api;
    requires javax.inject;

    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.webmvc;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.commons;

    //db e auto configuration
    requires spring.data.jpa;
    requires spring.boot.starter.data.jpa;
    requires spring.orm;
    requires spring.jdbc;
    requires java.persistence;
    requires org.hibernate.orm.core;

    requires swagger.ui;
    requires swagger.annotations;
    requires org.springdoc.openapi.ui;
    requires org.springdoc.openapi.common;
    requires org.springdoc.openapi.webmvc.core;
    requires spring.boot.starter.validation;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;


    requires org.apache.tomcat.embed.core;
    requires org.apache.commons.collections4;

    requires commons.lang;
    requires modelmapper;
    requires slf4j.api;
    requires org.yaml.snakeyaml;

    requires sistema;
    requires servicos;
    requires io.swagger.v3.oas.annotations;
    requires io.swagger.v3.oas.models;
    requires jdk.unsupported;
    requires org.apache.commons.lang3;
    requires spring.cloud.openfeign.core;

    exports conta.api;
    exports conta.api.domain.config;
    exports conta.api.exception;
    exports conta.api.domain.exception;
    exports conta.builds.dois;
    exports conta.builds.tresquatro;
    exports conta.api.resource.controller;
    exports conta.api.service;
    exports conta.api.util;
    exports conta.api.domain.response;
    exports conta.api.domain.request.change;
    exports conta.api.domain.request.consult;
    exports conta.api.domain.validator.req;
    exports conta.api.domain.validator.val;

    opens conta.api;
    opens conta.api.domain.config;
    opens conta.builds.dois;
    opens conta.builds.tresquatro;
    opens conta.api.domain.exception;
    opens conta.api.exception;
    opens conta.api.resource.controller;
    opens conta.api.service;
    opens conta.api.util;
    opens conta.api.domain.response;
    opens conta.api.domain.request.change;
    opens conta.api.domain.request.consult;
    opens conta.api.domain.validator.req;
    opens conta.api.domain.validator.val;
}