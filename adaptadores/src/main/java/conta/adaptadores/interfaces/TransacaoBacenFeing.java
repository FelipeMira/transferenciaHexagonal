package conta.adaptadores.interfaces;

import conta.adaptadores.domain.bacen.request.TransacaoBacenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "bacen", url = "${spring.cloud.openfeign.client.config.bacen.url}")
public interface TransacaoBacenFeing {

    @PostMapping
    ResponseEntity<?> postTransacao(@RequestBody TransacaoBacenRequest transacaoBacenRequest);
}
