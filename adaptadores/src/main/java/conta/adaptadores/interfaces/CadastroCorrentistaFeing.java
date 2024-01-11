package conta.adaptadores.interfaces;

import conta.adaptadores.domain.cadastro.response.CadastroResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cadastro", url = "${spring.cloud.openfeign.client.config.cadastro.url}")
public interface CadastroCorrentistaFeing {

    @GetMapping(path = "{id}")
    CadastroResponse getCadastro(@PathVariable("id") Integer id);
}
