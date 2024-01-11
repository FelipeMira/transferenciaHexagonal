package conta.sistema.porta;

import conta.sistema.dominio.modelo.Conta;

// Responsavel por definir a porta de saída (driven) de servicos de banco de dados.
public interface ContaRepositorio {
    Conta get(Integer numero);
    void update(Conta conta);
}

