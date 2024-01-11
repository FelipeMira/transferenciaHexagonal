package conta.api.exception;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import conta.api.domain.exception.ErroInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import conta.sistema.dominio.modelo.NegocioException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String MENSAGEM_GLOBAL_204 = "Nenhum conteúdo.";
    public static final String MENSAGEM_GLOBAL_400 = "Requisição inválida.";
    public static final String MENSAGEM_GLOBAL_401 = "Não autorizado.";
    public static final String MENSAGEM_GLOBAL_403 = "Não permitido.";
    public static final String MENSAGEM_GLOBAL_404 = "Recurso não encontrado.";
    public static final String MENSAGEM_GLOBAL_409 = "Objeto já existente.";
    public static final String MENSAGEM_GLOBAL_412 = "Pré condições não atendidas.";
    public static final String MENSAGEM_GLOBAL_500 = "Erro interno do sistema.";
    public static final String MENSAGEM_GLOBAL_406 = "Não aceitável.";
    private static final String TIMED_OUT = "timed out";

    private static final String FALHA_NO_REQUEST_MSG_PATTERN = "Falha no request: Objeto[%s] Campo[%s] Valor[%s]";
    private static final String FALHA_NO_REQUEST_MSG_PATTERN_CUSTOM = "Falha no request: Objeto[%s] %s";
    private static final String FALHA_NO_REQUEST_MSG_TYPE_MISMATCH = "Parâmetro enviado [%s] para o campo [%s] não corresponde ao tipo [%s]";

    @Autowired
    private AsyncLogException asyncLogException;

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.lang.Exception.class)
    public @ResponseBody ResponseEntity<ErroInfo> handleExceptionServerError(HttpServletRequest request, java.lang.Exception ex) {
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.asyncLogException.logAsync(ex);

        String message = MENSAGEM_GLOBAL_500;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (StringUtils.contains(ex.getMessage(), TIMED_OUT)) {
            httpStatus = HttpStatus.GATEWAY_TIMEOUT;
            message = TIMED_OUT;
        }

        return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(message)), httpStatus);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErroInfo handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {

        final List<String> messages = new ArrayList<>();
        Optional.of(ex.getBindingResult().getAllErrors()).orElse(emptyList()).forEach(objectError -> {
            if (objectError instanceof FieldError) {
                final FieldError field = (FieldError) objectError;
                messages.add(format(FALHA_NO_REQUEST_MSG_PATTERN, field.getObjectName(), field.getField(), field.getRejectedValue()));
            } else {
                //messages.add(format(FALHA_NO_REQUEST_MSG_PATTERN, objectError.getObjectName(), Arrays.toString(objectError.getArguments()), Arrays.toString(objectError.getCodes())));
                messages.add(format(FALHA_NO_REQUEST_MSG_PATTERN_CUSTOM, objectError.getObjectName(), objectError.getDefaultMessage()));
            }
        });

        return buildErrorInfo(request, ex, messages);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NegocioException.class)
    public @ResponseBody ErroInfo handleNegocioException(HttpServletRequest request, NegocioException ex) {
        return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public @ResponseBody ErroInfo handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        return buildErrorInfo(request, ex, singletonList(format(FALHA_NO_REQUEST_MSG_TYPE_MISMATCH, ex.getValue(), ex.getParameter().getParameterName(), ex.getParameter().getParameterType().getSimpleName())));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody ErroInfo handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        StringBuilder field = new StringBuilder();

        Throwable throwable = ex.getCause();
        JsonMappingException jsonMappingException = ((JsonMappingException) throwable);
        List<JsonMappingException.Reference> references = ((InvalidFormatException)throwable).getPath();
        for (JsonMappingException.Reference reference : references) {
            if (reference.getFieldName() != null) {
                field.append(reference.getFieldName()).append(" error [").append(jsonMappingException.getOriginalMessage()).append("]");
            }
        }

        return buildErrorInfo(request, ex, singletonList(field.toString()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ BadRequest.class, PreconditionCustom.class })
    public ResponseEntity<ErroInfo>handleException(HttpServletRequest request, Exception ex) {

        HttpStatus httpStatus = ex.getStatus();
        return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(ex.getMessage())), httpStatus);
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler({ Unauthorized.class })
    public @ResponseBody ErroInfo handleExceptionUnauthorized(HttpServletRequest request, Unauthorized ex) {
        return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public @ResponseBody ErroInfo handleBindException(HttpServletRequest request, BindException ex) {
        final BindingResult bindingResult = ex.getBindingResult();

        return buildErrorInfo(request, ex, bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }

    @ResponseStatus(BAD_GATEWAY)
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErroInfo> handleHttpServerErrorException(HttpServletRequest request, HttpServerErrorException ex) {

        String message = ex.getMessage();
        HttpStatus httpStatus = ex.getStatusCode();

        return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(message)), httpStatus);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErroInfo> handleHttpClientErrorException(HttpServletRequest request, HttpClientErrorException ex) {

        String message = null;
        HttpStatus httpStatus = ex.getStatusCode();
        ErroInfo error = null;

        try {
            error = new ObjectMapper().readValue(ex.getResponseBodyAsByteArray(), ErroInfo.class);
        } catch (IOException e) {
        }

        if (Objects.nonNull(error) && CollectionUtils.isNotEmpty(error.getMessages())) {
            message = error.getMessages().get(0);
        } else {

            switch (ex.getStatusCode()) {
                case BAD_REQUEST:
                    message = MENSAGEM_GLOBAL_400;
                    break;
                case NOT_FOUND:
                    message = MENSAGEM_GLOBAL_404;
                    break;
                case UNAUTHORIZED:
                    message = MENSAGEM_GLOBAL_401;
                    break;
                case FORBIDDEN:
                    message = MENSAGEM_GLOBAL_403;
                    break;
                case CONFLICT:
                    message = MENSAGEM_GLOBAL_409;
                    break;
                case UNPROCESSABLE_ENTITY:
                    message = ex.getMessage();
                    break;
                default:
                    httpStatus = HttpStatus.PRECONDITION_FAILED;
                    message = ex.getMessage();
                    break;
            }
        }
        return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(message)), httpStatus);
    }

    @ResponseStatus(PRECONDITION_FAILED)
    @ExceptionHandler(HttpClientErrorAnotherApiException.class)
    public ResponseEntity<ErroInfo> handleHttpClientErrorException(HttpServletRequest request, HttpClientErrorAnotherApiException ex) {
        return new ResponseEntity<>(buildErrorInfo(request, ex, singletonList(ex.getMessage())), ex.getStatus());
    }

    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler({ NoContentCustom.class })
    public @ResponseBody ErroInfo handleExceptionNoContent(HttpServletRequest request, NoContentCustom ex) {
        return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ NotFoundCustom.class })
    public @ResponseBody ErroInfo handleExceptionNoContent(HttpServletRequest request, NotFoundCustom ex) {
        return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({ Conflict.class })
    public @ResponseBody
    ErroInfo handleExceptionConflictPqa(HttpServletRequest request, Conflict ex) {
        return buildErrorInfo(request, ex, singletonList(ex.getMessage()));
    }

    private ErroInfo buildErrorInfo(HttpServletRequest request, java.lang.Exception exceptionPqa, List<String> messages) {
        return ErroInfo.builder()
                .timestamp(LocalDateTime.now())
                .messages(messages)
                .exception(exceptionPqa.getClass().getSimpleName())
                .path(request.getRequestURI()).build();
    }

}
