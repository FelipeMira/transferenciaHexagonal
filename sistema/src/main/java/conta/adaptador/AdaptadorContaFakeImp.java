package conta.adaptador;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.Correntista;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.porta.ContaRepositorio;
import javax.inject.Named;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.isNull;

// Responsavel por implementar a porta de saída (driven) de servicos de banco de dados falso.
// Sera gerenciado pelo IoC
@Named
public class AdaptadorContaFakeImp implements ContaRepositorio {
    private final Map<Integer, Conta> banco = new HashMap<>();

    public AdaptadorContaFakeImp() {
        banco.put(10, new Conta(10, new BigDecimal(2000), new Correntista(1), 1, LocalDate.now().minusDays(1), new BigDecimal(1000)));
        banco.put(20, new Conta(20, new BigDecimal(100), new Correntista(2), 1, LocalDate.now().minusDays(1), new BigDecimal(1000)));
        banco.put(30, new Conta(30, new BigDecimal(100), new Correntista(3), 0, LocalDate.now().minusDays(1), new BigDecimal(1000)));
    }
    public Conta get(Integer numero) {
        System.out.println("Fake banco de dados -> Conta get(numero)");
        return banco.get(numero);
    }
    public void update(Conta conta) {
        System.out.println("Fake banco de dados -> alterar(conta)");
        var ct = banco.get(conta.getNumero());
        if (!isNull(ct)) {
            banco.put(conta.getNumero(), conta);
        } else {
            throw new NegocioException("Conta inexistente: " + conta.getNumero());
        }
    }
}