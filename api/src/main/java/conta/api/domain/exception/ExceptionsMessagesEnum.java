package conta.api.domain.exception;

import conta.api.exception.*;
import conta.api.exception.Exception;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Getter
@Slf4j
public enum ExceptionsMessagesEnum {

    GLOBAL_ERRO_SERVIDOR(INTERNAL_SERVER_ERROR.value(), "Erro interno de servidor", Exception.class),
    GLOBAL_RECURSO_NAO_ENCONTRADO(NOT_FOUND.value(), "Recurso nao encontrado", NotFoundCustom.class),
    GLOBAL_RECURSO_EXISTENTE(CONFLICT.value(), "Recurso ja existe", Conflict.class),
    GLOBAL_PRECONDICAO_NAO_ATENDIDA(PRECONDITION_FAILED.value(), "Pre-condicao nao atendida", Exception.class),
    PESQUISA_NAO_ENCONTRADA(NO_CONTENT.value(), "Pesquisa nao retornou valores", Exception.class);

    private Integer code;
    @Setter
    private String message;
    private Class<? extends Exception> klass;

    ExceptionsMessagesEnum(int code, String message, Class<? extends Exception> klass) {
        this.message = message;
        this.klass = klass;
        this.code = code;
    }

    public static ExceptionsMessagesEnum getEnum(final String key) {
        return Arrays.stream(ExceptionsMessagesEnum.values())
                .filter(e -> StringUtils.equals(e.getMessage(), key))
                .findAny().orElse(null);
    }

    public void raise() throws Exception {

        log.debug("Raising error: {}", this);
        if (BAD_REQUEST.value() == this.code) {
            throw new BadRequest(this.message);
        } else if (CONFLICT.value() == this.code) {
            throw new Conflict(this.message);
        } else if (PRECONDITION_FAILED.value() == this.code) {
            throw new PreconditionCustom(this.message);
        } else if (NOT_FOUND.value() == this.code) {
            throw new NotFoundCustom(this.message);
        } else if (NO_CONTENT.value() == this.code) {
            throw new NoContentCustom(this.message);
        } else if (UNAUTHORIZED.value() == this.code) {
            throw new Unauthorized(this.message);
        } else {
            throw new Exception(INTERNAL_SERVER_ERROR, this.message);
        }
    }

    public void raise(String errorMessage) {
        this.setMessage(errorMessage);
        this.raise();
    }

}
