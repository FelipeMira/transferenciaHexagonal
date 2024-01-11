package conta.api.domain.request.consult;

import conta.api.domain.validator.req.PreReqContaConsultRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.io.Serializable;

@PreReqContaConsultRequest
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaCosultRequest implements Serializable {

    @Schema(description = "numero da conta",
            example = "1")
    private Integer numero;

}
