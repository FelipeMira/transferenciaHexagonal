// Responsavel por definir as regras de visibilidade do modulo sistema.
module sistema {
    //necessario para importar nas classes
    requires javax.inject;
    requires spring.tx;
    requires static lombok;

    // expondo sistema negocio
    exports conta.sistema.dominio.modelo;
    exports conta.sistema.dominio.servico;

    // expondo adptadores de saidas (driven)
    exports conta.sistema.porta;
    exports conta.adaptador;

    // expondo porta de entrada (driver)
    exports conta.sistema.casouso.porta;
    exports conta.sistema.casouso.imp;

    // abre reflexao do spring, para ficar visivel em outros projetos pelo IoC
    opens conta.sistema.casouso.porta;
    opens conta.sistema.casouso.imp;
    opens conta.sistema.dominio.servico;
    opens conta.adaptador;
}