package conta.api.domain.validator.req;

import conta.api.domain.validator.val.ContaConsultRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContaConsultRequestValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PreReqContaConsultRequest {

    String message() default "Deve ser enviado ao menos um identificador [id]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
