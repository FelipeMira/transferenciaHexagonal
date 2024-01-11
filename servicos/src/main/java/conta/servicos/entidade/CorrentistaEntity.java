package conta.servicos.entidade;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CorrentistaEntity {

    @Column(name = "correntista")
    private Integer idCorrentista;
    @Transient
    private String nome;
}
