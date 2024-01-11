package teste.casouso;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
// Responsavel por configurar os servicos do spring
@Configuration
// objetos de sistema// adptadores falsos
@ComponentScan({"conta.sistema", "conta.adaptador"})
public class BuildUm {
    // Build 1: Adaptador Testes -> Sistema <- Adptadores Mocks
}
