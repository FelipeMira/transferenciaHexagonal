package teste;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

// testando regras, servicos (driving) e portas de entrada (driver)
@SelectPackages({
        "teste.unidade.dominio.modelo",
        "teste.unidade.dominio.servico",
        "teste.casouso"})
@Suite
public class SuiteCore {
    // 100% da solucao testada independente de front-end e servicos externos (banco de dados)
}
