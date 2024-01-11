package conta.adaptadores.interfaces;

import conta.adaptadores.domain.bacen.request.TransacaoBacenRequest;
import conta.adaptadores.domain.cadastro.response.CadastroResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bacen", url = "${spring.cloud.openfeign.client.config.bacen.url}")
public interface TransacaoBacenFeing {

    @PostMapping
    void postTransacao(@RequestBody TransacaoBacenRequest transacaoBacenRequest);
}
