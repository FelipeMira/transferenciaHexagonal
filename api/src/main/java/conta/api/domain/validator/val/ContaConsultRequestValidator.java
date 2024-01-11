package conta.api.domain.validator.val;


import conta.api.domain.request.consult.ContaCosultRequest;
import conta.api.domain.validator.req.PreReqContaConsultRequest;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Slf4j
public class ContaConsultRequestValidator implements ConstraintValidator<PreReqContaConsultRequest, ContaCosultRequest> {

    @Override
    public void initialize(final PreReqContaConsultRequest arg0) {
        log.debug("Validator: {}", arg0);
    }

    @Override
    public boolean isValid(final ContaCosultRequest validatedClass, final ConstraintValidatorContext constraintValidatorContext) {

        return validatedClass.getNumero() != null;
    }

}

