package conta.servicos.entidade;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="conta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ContaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Column(name="saldo")
    private BigDecimal saldo;

    @Embedded
    private CorrentistaEntity correntista;

    @Column(name = "ativa")
    private Integer ativa;

    @Column(name = "data_atual")
    private LocalDate date;

    @Column(name="limite")
    private BigDecimal limiteDiario;

}
