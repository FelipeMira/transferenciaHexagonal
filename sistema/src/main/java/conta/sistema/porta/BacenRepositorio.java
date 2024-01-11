package conta.sistema.porta;
;
import conta.sistema.dominio.modelo.TransacaoBacen;

import java.math.BigDecimal;

// Responsavel por definir a porta de saída (driven) de servicos de API do BACEN.
public interface BacenRepositorio {
    void postTransferencia(TransacaoBacen transacaoBacen);
}
