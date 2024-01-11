package conta.adaptadores.repositorio;

import conta.adaptadores.domain.bacen.request.TransacaoBacenRequest;
import conta.adaptadores.interfaces.TransacaoBacenFeing;
import conta.adaptadores.utils.GenericConvert;
import conta.sistema.dominio.modelo.TransacaoBacen;
import conta.sistema.porta.BacenRepositorio;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BacenRepoImpl implements BacenRepositorio {

    private final TransacaoBacenFeing transacaoBacenFeing;

    @Inject
    public BacenRepoImpl(TransacaoBacenFeing transacaoBacenFeing){
        this.transacaoBacenFeing = transacaoBacenFeing;
    }

    @Override
    public void postTransferencia(TransacaoBacen transacaoBacen) {
        transacaoBacenFeing.postTransacao(GenericConvert.convertModelMapper(transacaoBacen, TransacaoBacenRequest.class));
    }
}
