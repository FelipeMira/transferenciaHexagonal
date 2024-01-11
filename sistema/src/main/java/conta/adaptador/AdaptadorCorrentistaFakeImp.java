package conta.adaptador;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.Correntista;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.porta.CadastroRepositorio;
import conta.sistema.porta.ContaRepositorio;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

// Responsavel por implementar a porta de saída (driven) de servicos de API falso.
// Sera gerenciado pelo IoC
@Named
public class AdaptadorCorrentistaFakeImp implements CadastroRepositorio {
    private final Map<Integer, Correntista> cadastro = new HashMap<>();

    public AdaptadorCorrentistaFakeImp() {
        cadastro.put(1, new Correntista(1, "Fernando Fake"));
        cadastro.put(2, new Correntista(2, "Rebeca Fake"));
    }
    public Correntista get(Integer idCorrentista) {
        System.out.println("Fake api cadastro -> Correntista get(idCorrentista)");
        return cadastro.get(idCorrentista);
    }

}