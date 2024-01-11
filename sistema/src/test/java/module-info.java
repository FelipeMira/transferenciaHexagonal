// Responsavel por definir as regras de visibilidade do modulo sistema.teste.
module sistema.teste {
    //necessario para importar nos testes
    requires javax.inject;
    requires spring.tx;
    requires spring.test;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.platform.engine;
    requires org.junit.platform.suite.engine;
    requires org.junit.platform.suite.api;
    requires org.junit.platform.commons;

    //modulo que iremos testar
    requires sistema;

    //modulo de teste que iremos executar
    exports teste.unidade.dominio.modelo;
    exports teste.unidade.dominio.servico;
    exports teste.casouso;
    exports teste;

    //abre reflexao do spring, para ficar visivel em outros projetos pelo IoC
    opens teste.unidade.dominio.modelo;
    opens teste.unidade.dominio.servico;
    opens teste.casouso;
    opens teste;
}