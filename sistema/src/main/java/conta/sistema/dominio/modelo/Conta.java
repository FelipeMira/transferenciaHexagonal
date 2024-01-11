package conta.sistema.dominio.modelo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static conta.sistema.dominio.modelo.Erro.*;
import static java.util.Objects.isNull;

// Responsavel por representar a entidade conta e suas regras.
// Nao sera gerenciado pelo IoC e sim pelo repositorio.
@AllArgsConstructor
@ToString
public class Conta {
    @Getter@Setter
    private Integer numero;
    @Getter@Setter
    private BigDecimal saldo;
    @Getter@Setter
    private Correntista correntista;
    @Getter@Setter
    private Integer ativa;
    @Getter@Setter
    private LocalDate date;
    @Getter@Setter
    private BigDecimal limiteDiario;

    public Conta() {
        numero = 0;
        saldo = BigDecimal.ZERO;
        correntista = new Correntista();
        date = LocalDate.now();
        limiteDiario = new BigDecimal(1000);
    }

    public void verificarContaAtiva(){
        if(this.getAtiva() == 0){
            inativa(this.getNumero());
        }
    }

    public void verificarData(){
        if(!isNull(date)){
            if(date.isBefore(LocalDate.now())){
                date = LocalDate.now();
                atualizarLimiteDiario();
            }
        }
    }

    private void atualizarLimiteDiario() {
        limiteDiario = new BigDecimal(1000);
    }

    public void creditar(BigDecimal credito) {
        if (isNull(credito)) {
            obrigatorio("Valor credito");
        }
        if (credito.compareTo(BigDecimal.ZERO) <= 0) {
            obrigatorio("Valor credito");
        }
        saldo = saldo.add(credito);
    }

    public void debitar(BigDecimal debito) {
        if (isNull(debito)) {
            obrigatorio("Valor debito");
        }
        if(debito.compareTo(limiteDiario) > 0){
            acimaDoLimiteDiario(limiteDiario);
        }
        if (debito.compareTo(BigDecimal.ZERO) <= 0) {
            obrigatorio("Valor debito");
        }
        if (debito.compareTo(saldo) > 0) {
            saldoInsuficiente();
        }
        saldo = saldo.subtract(debito);
        limiteDiario = limiteDiario.subtract(debito);
    }
}
