package conta.adaptador;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.TransacaoBacen;
import conta.sistema.porta.BacenRepositorio;

import javax.inject.Named;
import java.math.BigDecimal;

// Responsavel por implementar a porta de saída (driven) de servicos de API falso.
// Sera gerenciado pelo IoC
@Named
public class AdaptadorBacenFakeImp implements BacenRepositorio {

    @Override
    public void postTransferencia(TransacaoBacen transacaoBacen) {
        System.out.println("Fake api BACEN -> postTransferencia(transacaoBacen)");
    }
}