package conta.api.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class ContaResponse implements Serializable {

    @ApiModelProperty(value = "Numero da Conta", example = "1", position = 1)
    @JsonProperty("id")
    private Integer numero;

    @ApiModelProperty(value = "Saldo", position = 2)
    private BigDecimal saldo;

    @ApiModelProperty(value = "Correntista", position = 3)
    private CorrentistaResponse correntista;

}
