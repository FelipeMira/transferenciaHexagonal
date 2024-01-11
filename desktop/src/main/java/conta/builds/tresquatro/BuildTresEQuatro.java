package conta.builds.tresquatro;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@EnableAutoConfiguration
@ComponentScan({// adptadores front-end javafx
        "conta.javafx",
        // adptadores reais
        "conta.servicos.config"})
public class BuildTresEQuatro {

}
