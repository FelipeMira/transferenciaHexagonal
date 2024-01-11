package conta.sistema.dominio.servico;

import conta.sistema.dominio.modelo.Conta;

import javax.inject.Named;
import java.math.BigDecimal;

import static conta.sistema.dominio.modelo.Erro.obrigatorio;
import static java.util.Objects.isNull;

// Responsavel por representar a entidade transferencia e suas regras.
// Sera gerenciado pelo IoC
@Named
public class Transferencia {
    public void processar(BigDecimal valor, Conta debito, Conta credito) {
        if (isNull(valor)) {
            obrigatorio("Valor da transferencia");
        }
        if (isNull(debito)) {
            obrigatorio("Conta debito");
        }
        debito.verificarContaAtiva();

        if (isNull(credito)) {
            obrigatorio("Conta credito");
        }
        credito.verificarContaAtiva();

        debito.verificarData();

        debito.debitar(valor);
        credito.creditar(valor);
    }
}

