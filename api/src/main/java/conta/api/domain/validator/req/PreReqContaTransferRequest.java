package conta.api.domain.validator.req;

import conta.api.domain.validator.val.ContaTransferRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContaTransferRequestValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PreReqContaTransferRequest {

    String message() default "Deve ser enviado valorTransferencia no formato 00.00";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
