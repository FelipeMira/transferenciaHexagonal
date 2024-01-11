package conta.sistema.porta;

import conta.sistema.dominio.modelo.Correntista;

// Responsavel por definir a porta de saída (driven) de servicos de API de cadastro.
public interface CadastroRepositorio {
    Correntista get(Integer numero);
}

