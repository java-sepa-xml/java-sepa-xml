package org.simple.sepa.validator.exception;

public class SEPAValidatorIBANFormatException extends RuntimeException {

    public SEPAValidatorIBANFormatException() {
        super();
    }

    public SEPAValidatorIBANFormatException(String message) {
        super(message);
    }
}
