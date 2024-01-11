package conta.api.domain.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Resposta de erro da API")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
public class ErroInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Data e hora do erro", example = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime timestamp;

    @ApiModelProperty(value = "Código do erro", example = "404")
    public Integer code;

    @ApiModelProperty(value = "Exceção lançada", example = "\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\"")
    public String exception;

    @ApiModelProperty(value = "Lista de mensagens de erro", example = "[ { \"Requisição inválida.\" } ] ")
    public List<String> messages;

    @ApiModelProperty(value = "Path da chamada que ocasionou o erro", example = "\\path")
    public String path;

}

