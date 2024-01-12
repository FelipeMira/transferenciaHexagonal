package conta.adaptadores.repositorio;

import conta.adaptadores.domain.bacen.request.TransacaoBacenRequest;
import conta.adaptadores.interfaces.TransacaoBacenFeing;
import conta.adaptadores.utils.GenericConvert;
import conta.sistema.dominio.modelo.TransacaoBacen;
import conta.sistema.porta.BacenRepositorio;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BacenRepoImpl implements BacenRepositorio {

    private final TransacaoBacenFeing transacaoBacenFeing;

    private final RabbitTemplate rabbitTemplate;

    private static final String queueName = "bacen-queue";

    @Inject
    public BacenRepoImpl(TransacaoBacenFeing transacaoBacenFeing, RabbitTemplate rabbitTemplate){
        this.transacaoBacenFeing = transacaoBacenFeing;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void postTransferencia(TransacaoBacen transacaoBacen) {
        try{
            transacaoBacenFeing.postTransacao(GenericConvert.convertModelMapper(transacaoBacen, TransacaoBacenRequest.class));
        }catch(Exception ex){
            Gson gson = new Gson();
            rabbitTemplate.convertAndSend(queueName, gson.toJson(transacaoBacen));
        }
    }
}
