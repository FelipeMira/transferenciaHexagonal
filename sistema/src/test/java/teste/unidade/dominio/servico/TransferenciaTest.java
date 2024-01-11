package teste.unidade.dominio.servico;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.dominio.servico.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Transferencia")
public class TransferenciaTest {
    // armazena o cem para teste ficar dinamico
    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Transferencia trans = new Transferencia();
    Conta contaDebito;
    Conta contaCredito;

    @BeforeEach
    void prepara() {
        contaDebito = new Conta(1, cem, "Fernando");
        contaCredito = new Conta(2, cem, "Rebeca");
        trans = new Transferencia();
    }

    // negativos
    @Test
    @DisplayName("Valor nulo como obrigatorio")
    void valorNuloObrigatorio() {
        try {
            trans.processar(null, contaDebito, contaCredito);
            fail("valor transferencia como obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor da transferencia e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    // Observaçao:
    // Nao se faz necessario refazer os testes de nulo, zero ou negativo, na transferencia,
    // pois ele repassa para conta.debitar() e conta.creditar() os testes desses ja garantem isso.
    // Cada teste deve garantir o serviço implementadas dentro da classe a ser testada e nao
    // testar coisas de classes agregadas.
    @Test
    @DisplayName("Conta debito como obrigatorio")
    void contaDebitoObrigatorio() {
        try {
            trans.processar(vinte, null, contaCredito);
            fail("conta debito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta debito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Conta credito como obrigatorio")
    void contaCreditoObrigatorio() {
        try {
            trans.processar(vinte, contaDebito, null);
            fail("conta credito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta credito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    
    // positivos
    @Test
    @DisplayName("Transferir valor positivo")
    void transferirValorPositivo() {
        try {
            trans.processar(vinte, contaDebito, contaCredito);
            assertEquals(contaDebito.getSaldo(), cem.subtract(vinte),
                    "Saldo da conta debito deve bater");
            assertEquals(contaCredito.getSaldo(), cem.add(vinte),
                    "Saldo da conta credito deve bater");
        } catch (NegocioException e) {
            fail("Deve transferir com sucesso");
        }
    }
}

