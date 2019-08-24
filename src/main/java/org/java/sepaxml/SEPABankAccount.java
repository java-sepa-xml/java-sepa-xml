package org.java.sepaxml;

import org.java.sepaxml.format.SEPAFormatFilter;
import org.java.sepaxml.validator.SEPAValidatorIBAN;
import org.java.sepaxml.validator.SEPAValidatorBIC;
import org.java.sepaxml.validator.exception.SEPAValidatorIBANFormatException;

public class SEPABankAccount {

    private String IBAN;
    private String BIC;
    private String name;

    public String getIBAN() {
        return IBAN;
    }

    public String getBIC() {
        return BIC;
    }

    public String getName() {
        return name;
    }

    public SEPABankAccount(String IBAN, String name) {
        this(IBAN, null, name);
    }

    public SEPABankAccount(String IBAN, String BIC, String name) {
        if (SEPAValidatorIBAN.isValid(IBAN)) {
            this.IBAN = SEPAFormatFilter.filter(IBAN);
        } else {
                throw new SEPAValidatorIBANFormatException("Invalid IBAN: " + IBAN);
        }

        if (BIC != null && SEPAValidatorBIC.isValid(BIC)) {
            this.BIC = SEPAFormatFilter.filterBIC(BIC);
        }

        this.name = name;
    }
}
