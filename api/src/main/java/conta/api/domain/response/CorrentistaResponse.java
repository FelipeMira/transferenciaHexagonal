package conta.api.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class CorrentistaResponse implements Serializable {

    @ApiModelProperty(value = "Nome Correntista", position = 3)
    private String nome;
}
