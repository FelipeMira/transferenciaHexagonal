package conta.adaptadores.domain.bacen.request;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoBacenRequest implements Serializable {

        private Integer idDebito;
        private Integer idCredito;
        private BigDecimal valor;
}
