package conta.api.domain.validator.val;


import conta.api.domain.request.change.ContaTransferRequest;
import conta.api.domain.validator.req.PreReqContaTransferRequest;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Slf4j
public class ContaTransferRequestValidator implements ConstraintValidator<PreReqContaTransferRequest, ContaTransferRequest> {

    @Override
    public void initialize(final PreReqContaTransferRequest arg0) {
        log.debug("Validator: {}", arg0);
    }

    @Override
    public boolean isValid(final ContaTransferRequest validatedClass, final ConstraintValidatorContext constraintValidatorContext) {
        return validatedClass.getValorTransferencia() != null;
    }

}

