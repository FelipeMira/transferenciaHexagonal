module adaptadores {
    requires lombok;
    requires spring.cloud.openfeign.core;
    requires spring.web;
    requires spring.boot.starter;
    requires sistema;
    requires javax.inject;
    requires modelmapper;
    requires spring.context;
    requires spring.data.redis;
    requires spring.rabbit;
    requires spring.amqp;
    requires com.google.gson;

    exports conta.adaptadores.config;
    exports conta.adaptadores.repositorio;
    exports conta.adaptadores.interfaces;
    exports conta.adaptadores.domain.cadastro.response;
    exports conta.adaptadores.domain.bacen.request;

    opens conta.adaptadores.config;
    opens conta.adaptadores.repositorio;
    opens conta.adaptadores.interfaces;
    opens conta.adaptadores.domain.cadastro.response;
    opens conta.adaptadores.domain.bacen.request;
}