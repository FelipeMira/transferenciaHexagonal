package conta.api.resource.controller;

import conta.api.domain.request.change.ContaTransferRequest;
import conta.api.resource.definition.TransferenciaDefinition;
import conta.api.service.ContaService;
import conta.api.util.AppConstantes;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = AppConstantes.PATH_TRANSFERENCIA)
public class TransferenciaController implements TransferenciaDefinition {

    private final ContaService contaService;

    @Inject
    public TransferenciaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @Override
    @ResponseStatus(OK)
    @PostMapping(value = "/{idDebito}/contas/{idCredito}")
    public String transferir(@Parameter(description="Id da conta de debito", required=true) @PathVariable Integer idDebito,
                             @Parameter(description="Id da conta de credito", required=true) @PathVariable Integer idCredito,
                             @Parameter(description="Valor da transferencia",
                                     required=true, schema=@Schema(implementation = ContaTransferRequest.class))
                                 @Valid @RequestBody ContaTransferRequest contaTransferRequest) {

        return contaService.transferir(idDebito, idCredito, contaTransferRequest);
    }


}
