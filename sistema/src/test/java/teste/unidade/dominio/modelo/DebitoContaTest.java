package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.Correntista;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Debito de Conta")
public class DebitoContaTest {
    // armazena o saldo para teste ficar dinamico
    BigDecimal cem = new BigDecimal(700);
    Conta contaValida;

    @BeforeEach
    void prepara() {
        contaValida = new Conta(10, cem, new Correntista(1), 1, LocalDate.now().minusDays(1), new BigDecimal(1000));
    }
    
    // negativos
    @Test
    @DisplayName("Valor debito nulo como obrigatorio")
    void valorDebitoNuloObrigatorio() {
        try {
            contaValida.debitar(null);
            fail("valor debito e obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor debito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Valor debito negativo como obrigatorio")
    void valorDebitoNegativoObrigatorio() {
        try {
            contaValida.debitar(new BigDecimal(-10));
            fail("valor debito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor debito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Valor debito zero como obrigatorio")
    void valorDebitoZeroObrigatorio() {
        try {
            contaValida.debitar(BigDecimal.ZERO);
            fail("valor debito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor debito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Valor debito acima do saldo")
    void valorDebitoAcimaDoSaldo() {
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("valor debito acima do saldo");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Saldo insuficiente.");
            System.out.println(e.getMessage());
        }
    }
    
    // positivos
    @Test
    @DisplayName("Valor debito igual ao saldo")
    void valorDebitoIgualAoSaldo() {
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "Saldo deve zerar");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Valor debito menor que saldo")
    void valorDebitoMenorQueSaldo() {
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }
}
