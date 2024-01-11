package conta.servicos.repositorio;

import conta.servicos.entidade.ContaEntity;
import conta.servicos.interfaces.ContaCrudRepository;
import conta.servicos.utils.map.GenericConvert;
import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.porta.ContaRepositorio;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.Optional;

import static java.util.Objects.isNull;

// Responsavel por implementar a porta de saida (driven) de servicos de banco de dados usando spring jdbc
@Named
public class ContaRepositorioImp implements ContaRepositorio {

    private static final String ERRO = "Erro inesperado de acesso ao banco. Entre em contato com adminstrador.";

    private final ContaCrudRepository contaCrudRepository;


    @Inject
    public ContaRepositorioImp(ContaCrudRepository contaCrudRepository) {
        this.contaCrudRepository = contaCrudRepository;
    }
    @Override
    public Conta get(Integer numero) {
        if (isNull(numero)) {
            return null;
        }
        try{
            Optional<ContaEntity> optionalContaEntity = contaCrudRepository.findById(numero);

            return GenericConvert.convertModelMapper(optionalContaEntity.isEmpty()? null : optionalContaEntity.get(), Conta.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }

    @Transactional
    @Override
    public void update(Conta conta) {
        if (isNull(conta)) {
            throw new NegocioException("Conta e obrigatorio.");
        }
        try {
            contaCrudRepository.saveAndFlush(GenericConvert.convertModelMapper(conta, ContaEntity.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new NegocioException(ERRO);
        }
    }
}

