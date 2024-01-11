package teste.casouso;

import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.inject.Inject;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Caso de Uso - Servico de Transferencia")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BuildUm.class)
public class AdaptadorTransferenciaTest {
    Integer contaCredito = 10;
    Integer contaDebito = 20;
    Integer contaInexistente = 30;
    BigDecimal cem = new BigDecimal(100);
    BigDecimal cinquenta = new BigDecimal(50);
    @Inject
    PortaTransferencia porta;

    // negativos get conta
    @Test
    @DisplayName("Pesquisa conta com numero nulo")
    void pesquisaContaComNumeroNulo() {
        try {
            var conta = porta.getConta(null);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }

    @Test
    @DisplayName("pesquisa conta com numero inexistente")
    void pesquisaContaComNumeroInexistente() {
        try {
            var conta = porta.getConta(contaInexistente);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }

    // positivo get conta
    @Test
    @DisplayName("Pesquisa conta com numero existente")
    void pesquisaContaComNumeroExistente() {
        try {
            var conta = porta.getConta(contaCredito);
            assertNotNull(conta, "Conta deve estar preenchida");
            System.out.println(conta);
        } catch (NegocioException e) {
            fail("Deve carregar uma conta existente.");
        }
    }

    // negativos transferencia
    @Test
    @DisplayName("Conta credito como obrigatorio")
    void contaCreditoComoObrigatorio() {
        try {
            porta.transferir(null, contaCredito, cinquenta);
            fail("Conta debito e obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta debito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("conta debito como obrigatorio")
    void contaDebitoComoObrigatorio() {
        try {
            porta.transferir(contaDebito, null, cinquenta);
            fail("Conta credito e obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta credito e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor como obrigatorio")
    void valorComoObrigatorio() {
        try {
            porta.transferir(contaDebito, contaCredito, null);
            fail("Valor e obrigatorio");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta debito inexistente")
    void contaDebitoInexistente() {
        try {
            porta.transferir(contaInexistente, contaCredito, cinquenta);
            fail("Conta debito inexistente");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta debito e inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Conta credito inexistente")
    void contaCreditoInexistente() {
        try {
            porta.transferir(contaDebito, contaInexistente, cinquenta);
            fail("Conta credito e inexistente");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta credito e inexistente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Mesma conta debito e credito")
    void mesmaContaDebitoECredito() {
        try {
            porta.transferir(contaDebito, contaDebito, cinquenta);
            fail("Conta credito e debito deve ser diferentes");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta debito e credito devem ser diferentes.");
                    System.out.println(e.getMessage());
        }
    }

    // Observacao:
    // Nao se faz necessario refazer os testes de valor nulo, zero ou negativo, no caso de uso, pois ele repassa os
    // objetos internos de domínio ja testados. Cada teste deve garantir o servico implementadas dentro da classe a
    // ser testada e nao testar coisas de classes agregadas.
    // positivo transferencia
    @Test
    @DisplayName("Transferencia valor disponivel e contas validas")
    void transferenciaValorDisponivelEContasValidas() {
        try {
            porta.transferir(contaDebito, contaCredito, cinquenta);
        } catch (NegocioException e) {
            fail("Nao deve gerar erro de transferencia - " + e.getMessage());
        }
        try {
            var credito = porta.getConta(contaCredito);
            var debito = porta.getConta(contaDebito);
            assertEquals(credito.getSaldo(), cem.add(cinquenta), "Saldo credito deve bater");
                    assertEquals(debito.getSaldo(), cem.subtract(cinquenta), "Saldo debito deve bater");
        } catch (NegocioException e) {
            fail("Nao deve gerar erro de validacao de saldo - " + e.getMessage());
        }
    }
}

