package conta.builds.dois;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Responsavel por configurar os servicos do spring
@Configuration
@ComponentScan({
        // adptadores front-end javafx
        "conta.javafx",
        // core do sistema
        "conta.sistema",
        // adptadores falsos
        "conta.adaptador"})
public class BuildDois {
    // Build Dois - Adaptador JavaFX -> Sistema <- Adaptadores Mocks
}
