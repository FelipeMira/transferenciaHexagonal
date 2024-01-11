package teste.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        // adptadores front-end javafx
        "conta.javafx",
        // core do sistema
        "conta.sistema",
        // adptadores falsos
        "conta.adaptador"})
public class TestConfig {

}
