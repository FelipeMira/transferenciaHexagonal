package conta.api.service;

import conta.api.domain.exception.ExceptionsMessagesEnum;
import conta.api.domain.request.change.ContaTransferRequest;
import conta.api.domain.response.ContaResponse;
import conta.api.exception.NoContentCustom;
import conta.api.util.GenericConvert;
import conta.sistema.casouso.porta.PortaTransferencia;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ContaService {

    private final PortaTransferencia portaTransferencia;

    @Inject
    public ContaService(PortaTransferencia portaTransferencia) {
        this.portaTransferencia = portaTransferencia;
    }

    public ContaResponse buscarConta(Integer numero) {
        ContaResponse contaResponse = GenericConvert.convertModelMapper(portaTransferencia.getConta(numero), ContaResponse.class);

        NoContentCustom.checkThrow((contaResponse == null),
                ExceptionsMessagesEnum.GLOBAL_RECURSO_NAO_ENCONTRADO);

        return contaResponse;
    }

    public String transferir(Integer idDebito, Integer idCredito, ContaTransferRequest contaTransferRequest) {

        String mensagem = "";

        portaTransferencia.transferir(idDebito, idCredito, contaTransferRequest.getValorTransferencia());
        mensagem = "Transferencia realizada com sucesso";


        return mensagem;
    }

}
