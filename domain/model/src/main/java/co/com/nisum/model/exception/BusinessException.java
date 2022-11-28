package co.com.nisum.model.exception;

import co.com.nisum.model.exception.message.BusinessErrorMessage;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private final BusinessErrorMessage businessErrorMessage;

    public BusinessException(BusinessErrorMessage businessErrorMessage) {
        super(businessErrorMessage.getMessage());
        this.businessErrorMessage = businessErrorMessage;
    }
}
