package teste.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableFeignClients("conta.adaptadores.interfaces")
@ImportAutoConfiguration({FeignAutoConfiguration.class})

@ComponentScan({
        "conta.adaptadores"})
public class BuildUm {

}
