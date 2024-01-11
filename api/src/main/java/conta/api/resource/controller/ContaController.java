package conta.api.resource.controller;

import conta.api.domain.response.ContaResponse;
import conta.api.resource.definition.ContaDefinition;
import conta.api.service.ContaService;
import conta.api.util.AppConstantes;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = AppConstantes.PATH_CONTAS)
public class ContaController implements ContaDefinition {


    private final ContaService contaService;

    @Inject
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Override
    @ResponseStatus(OK)
    @GetMapping(produces= APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<?> consultarConta(
            @Valid @Parameter(description="id da conta", required=true) @PathVariable Integer id) throws JsonParseException {
        ContaResponse response = contaService.buscarConta(id);

        return ResponseEntity.ok(response);
    }


}
