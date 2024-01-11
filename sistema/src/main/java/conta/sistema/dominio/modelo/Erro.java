package conta.sistema.dominio.modelo;

import java.math.BigDecimal;

// Responsavel por centralizar todas as mensagens de validacoes.
public class Erro {
    // erros genericos
    public static void obrigatorio(String nome) {
        throw new NegocioException(nome + " e obrigatorio.");
    }

    public static void inexistente(String nome) {
        throw new NegocioException(nome + " e inexistente.");
    }

    public static void correntistaInexistente(Integer idCorrentista) {
        throw new NegocioException("O correntista com id " + idCorrentista + " e inexistente.");
    }

    public static void inativa(Integer idConta) {
        throw new NegocioException("A conta " + idConta + " esta inativa.");
    }

    // erros especificos
    public static void saldoInsuficiente() {
        throw new NegocioException("Saldo insuficiente.");
    }

    public static void mesmaConta() {
        throw new NegocioException("Conta debito e credito devem ser diferentes.");
    }

    public static void dataNaoAtual() {
        throw new NegocioException("Data deve ser do dia atual.");
    }

    public static void acimaDoLimiteDiario(BigDecimal limite) {
        throw new NegocioException("Transacao acima do limite diario restante: R$" + limite + " .");
    }
}
