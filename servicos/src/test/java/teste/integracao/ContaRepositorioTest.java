package teste.integracao;

import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.porta.ContaRepositorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

//@Rollback(false)
@Transactional
@SpringBootTest
@DisplayName("Serviço de banco de dados - Conta")
@ContextConfiguration(classes = Config.class)
public class ContaRepositorioTest {
    @Inject
    ContaRepositorio rep;

    // negativos
    @Test
    @DisplayName("pesquisa conta com número nulo")
    void teste1() {
        try {
            var conta = rep.get(null);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }
    @Test
    @DisplayName("pesquisa conta com número inexistente")
    void teste2() {
        try {
            var conta = rep.get(8547);
            assertNull(conta, "Conta deve ser nula");
        } catch (NegocioException e) {
            fail("Deve carregar uma conta nula.");
        }
    }
    // positivo
    @Test
    @DisplayName("pesquisa conta com número existente")
    void teste3() {
        try {
            var conta = rep.get(1);
            assertNotNull(conta, "Conta deve estar preenchida");
            System.out.println(conta);
        } catch (NegocioException e) {
            fail("Deve carregar uma conta.");
        }
    }
    // negativos
    @Test
    @DisplayName("alterar conta como nulo")
    void teste4() {
        try {
            rep.update(null);
            fail("Nao deve alterar conta nula");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta e obrigatorio.");
            System.out.println(e.getMessage());
        }
    }
    // positivo
    @Test
    @DisplayName("alterar conta com sucesso")
    void teste5() {
        try {
            var c1 = rep.get(1);
            c1.setSaldo(new BigDecimal("100.00"));
            c1.setCorrentista("Anny Banco teste");
            rep.update(c1);
            var c2 = rep.get(2);
            assertEquals(c1.getSaldo(), c2.getSaldo(), "Deve bater o saldo");
            assertEquals(c1.getCorrentista(), c2.getCorrentista(), "Deve bater o correntista");
        } catch (NegocioException e) {
            fail("Deve alterar conta ");
        }
    }
}
