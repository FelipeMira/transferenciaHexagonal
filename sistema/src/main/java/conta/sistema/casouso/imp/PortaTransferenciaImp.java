package conta.sistema.casouso.imp;

import conta.sistema.casouso.porta.PortaTransferencia;
import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.TransacaoBacen;
import conta.sistema.dominio.servico.Transferencia;
import conta.sistema.porta.BacenRepositorio;
import conta.sistema.porta.CadastroRepositorio;
import conta.sistema.porta.ContaRepositorio;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import static conta.sistema.dominio.modelo.Erro.*;
import static java.util.Objects.isNull;

// Responsavel por implementar a porta de operacoes para caso de uso de transferencia.
// Sera gerenciado pelo IoC
@Named
public class PortaTransferenciaImp implements PortaTransferencia {
    private final ContaRepositorio repositorio;
    private final CadastroRepositorio cadastro;
    private final Transferencia transferencia;
    private final BacenRepositorio bacenRepositorio;

    // Ioc por construtor
    @Inject
    public PortaTransferenciaImp(ContaRepositorio repositorio, CadastroRepositorio cadastro, Transferencia transferencia, BacenRepositorio bacenRepositorio) {
        this.repositorio = repositorio;
        this.cadastro = cadastro;
        this.transferencia = transferencia;
        this.bacenRepositorio = bacenRepositorio;
    }

    @Override
    public Conta getConta(Integer numero) {
        if(isNull(numero)){
            obrigatorio("Numero da conta");
        }
        var conta = repositorio.get(numero);
        if(isNull(conta)){
            inexistente(numero.toString());
        }

        if(conta.getAtiva() == 0){
            inativa(numero);
        }

        var correntista = cadastro.get(conta.getCorrentista().getIdCorrentista());

        if(isNull(correntista)){
            correntistaInexistente(conta.getCorrentista().getIdCorrentista());
        }

        conta.setCorrentista(correntista);

        return conta;
    }

    @Override
    public void transferir(Integer contaDebito, Integer contaCredito, BigDecimal valor)
    {
        //1. validação de parametros
        if (isNull(contaDebito)) {
            obrigatorio("Conta debito");
        }

        if (isNull(contaCredito)) {
            obrigatorio("Conta credito");
        }

        if (isNull(valor)) {
            obrigatorio("Valor");
        }

        //2. validação de contas
        var debito = repositorio.get(contaDebito);

        if (isNull(debito)) {
            inexistente("Conta debito");
        }

        var credito = repositorio.get(contaCredito);

        if (isNull(credito)) {
            inexistente("Conta credito");
        }

        //3.validacao mesma conta
        if (debito.getNumero().equals(credito.getNumero())) {
            mesmaConta();
        }

        //4. operação
        transferencia.processar(valor, debito, credito);
        repositorio.update(debito);
        repositorio.update(credito);

        //5. registro da operação no BACEN
        bacenRepositorio.postTransferencia(new TransacaoBacen(debito.getNumero(), credito.getNumero(), valor));
    }
}


