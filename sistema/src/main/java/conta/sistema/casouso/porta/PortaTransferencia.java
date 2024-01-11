package conta.sistema.casouso.porta;

import conta.sistema.dominio.modelo.Conta;
import java.math.BigDecimal;

// Responsavel por definir a porta de entrada (driver) de operacoes para caso de uso de transferencia.
public interface PortaTransferencia {
    Conta getConta(Integer numero);
    void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor);
}
