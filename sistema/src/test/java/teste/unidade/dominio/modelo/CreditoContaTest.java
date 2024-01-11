package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Credito de Conta")
public class CreditoContaTest {
    // armazena o saldo para teste ficar dinamico
    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void prepara() {
        contaValida = new Conta(1, cem, "Rebeca");
    }

    // negativos
    @Test
    @DisplayName("Valor credito nulo como obrigatorio")
    void valorCraditoNulo() {
        try {
            contaValida.creditar(null);
            fail("valor credito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor credito e obrigatorio.");

            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor credito negativo como obrigatorio")
    void valorCreditoNegativo() {
        try {
            contaValida.creditar(new BigDecimal(-10));
            fail("valor credito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor credito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor credito zero como obrigatorio")
    void valorCreditoZero() {
        try {
            contaValida.creditar(BigDecimal.ZERO);
            fail("valor credito obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor credito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    // positivos
    @Test
    @DisplayName("Valor credito acima de zero")
    void valorCreditoMaiorQueZero() {
        try {
            contaValida.creditar(BigDecimal.ONE);
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        } catch (NegocioException e) {
            fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }
}
