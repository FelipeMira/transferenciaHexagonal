package conta.adaptadores.repositorio;

import conta.adaptadores.domain.cadastro.response.CadastroResponse;
import conta.adaptadores.interfaces.CadastroCorrentistaFeing;
import conta.adaptadores.utils.GenericConvert;
import conta.sistema.dominio.modelo.Correntista;
import conta.sistema.porta.CadastroRepositorio;
import org.springframework.cache.annotation.Cacheable;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class CadastroRepoImpl implements CadastroRepositorio {

    private final CadastroCorrentistaFeing cadastroCorrentistaFeing;

    @Inject
    public CadastroRepoImpl(CadastroCorrentistaFeing cadastroCorrentistaFeing){
        this.cadastroCorrentistaFeing = cadastroCorrentistaFeing;
    }

    @Override
    @Cacheable( value = "Correntista", key = "#idCorrentista")
    public Correntista get(Integer idCorrentista) {

        Optional<CadastroResponse> optionalCadastroResponse = Optional.ofNullable(cadastroCorrentistaFeing.getCadastro(idCorrentista));

        return GenericConvert.convertModelMapper(optionalCadastroResponse.orElse(null), Correntista.class);
    }
}
