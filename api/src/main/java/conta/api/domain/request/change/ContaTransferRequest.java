package conta.api.domain.request.change;

import conta.api.domain.validator.req.PreReqContaTransferRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@PreReqContaTransferRequest
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaTransferRequest implements Serializable {

    @Schema(description = "Valor da transferencia",
            example = "10.00")
    private BigDecimal valorTransferencia;

}
