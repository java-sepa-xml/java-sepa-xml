package org.simple.sepa.validator;

import org.simple.sepa.validator.exception.SEPAValidatorBICFormatException;

public class SEPAValidatorBIC {

    private static final int BIC8_LENGTH = 8;
    private static final int BIC11_LENGTH = 11;

    private static final int BANK_CODE_INDEX = 0;
    private static final int BANK_CODE_LENGTH = 4;
    private static final int COUNTRY_CODE_INDEX = BANK_CODE_INDEX + BANK_CODE_LENGTH;
    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int LOCATION_CODE_INDEX = COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH;
    private static final int LOCATION_CODE_LENGTH = 2;
    private static final int BRANCH_CODE_INDEX = LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH;
    private static final int BRANCH_CODE_LENGTH = 3;

    /**
     * Validates bic.
     *
     * @param bic to be validated.
     * @throws SEPAValidatorBICFormatException if bic is invalid.
     *                                         UnsupportedCountryException if bic's country is not supported.
     */
    public static boolean isValid(final String bic) throws SEPAValidatorBICFormatException {
        try {
            validateEmpty(bic);
            validateLength(bic);
            validateCase(bic);
            validateBankCode(bic);
            validateCountryCode(bic);
            validateLocationCode(bic);

            if (hasBranchCode(bic)) {
                validateBranchCode(bic);
            }
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }

    private static void validateEmpty(final String bic) {
        if (bic == null) {
            throw new SEPAValidatorBICFormatException("Null can't be a valid Bic.");
        }

        if (bic.length() == 0) {
            throw new SEPAValidatorBICFormatException("Empty string can't be a valid Bic.");
        }
    }

    private static void validateLength(final String bic) {
        if (bic.length() != BIC8_LENGTH && bic.length() != BIC11_LENGTH) {
            throw new SEPAValidatorBICFormatException(
                    String.format("Bic length must be %d or %d",
                            BIC8_LENGTH, BIC11_LENGTH));
        }
    }

    private static void validateCase(final String bic) {
        if (!bic.equals(bic.toUpperCase())) {
            throw new SEPAValidatorBICFormatException(
                    "Bic must contain only upper case letters.");
        }
    }

    private static void validateBankCode(final String bic) {
        String bankCode = getBankCode(bic);
        for (final char ch : bankCode.toCharArray()) {
            if (!Character.isLetter(ch)) {
                throw new SEPAValidatorBICFormatException("Bank code must contain only letters.");
            }
        }
    }

    private static void validateCountryCode(final String bic) {
        final String countryCode = getCountryCode(bic);
        if (countryCode.trim().length() < COUNTRY_CODE_LENGTH ||
                !countryCode.equals(countryCode.toUpperCase()) ||
                !Character.isLetter(countryCode.charAt(0)) ||
                !Character.isLetter(countryCode.charAt(1))
        ) {
            throw new SEPAValidatorBICFormatException("Bic country code must contain upper case letters");
        }

        String[] validCountries = new String[]{
                "AD", "AE", "AT", "AZ", "BA", "BE", "BG", "BH", "BR",
                "CH", "CR", "CY", "CZ", "DE", "DK", "DO", "EE", "ES",
                "FI", "FO", "FR", "GB", "GI", "GL", "GR", "GT", "HR",
                "HU", "IE", "IL", "IS", "IT", "JO", "KW", "KZ", "LB",
                "LI", "LT", "LU", "LV", "MC", "MD", "ME", "MK", "MR",
                "MT", "MU", "NL", "NO", "PK", "PL", "PS", "PT", "QA",
                "RO", "RS", "SA", "SE", "SI", "SK", "SM", "TN", "TR"
        };

        for (String code : validCountries) {
            if (code.equals(countryCode)) {
                return;
            }
        }

        throw new SEPAValidatorBICFormatException("Country code is not supported.");
    }

    private static void validateLocationCode(final String bic) {
        final String locationCode = getLocationCode(bic);
        for (char ch : locationCode.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {
                throw new SEPAValidatorBICFormatException("Location code must contain only letters or digits.");
            }
        }
    }

    private static void validateBranchCode(final String bic) {
        final String branchCode = getBranchCode(bic);
        for (final char ch : branchCode.toCharArray()) {
            if (!Character.isLetterOrDigit(ch)) {
                throw new SEPAValidatorBICFormatException("Branch code must contain only letters or digits.");
            }
        }
    }

    private static String getBankCode(final String bic) {
        return bic.substring(BANK_CODE_INDEX, BANK_CODE_INDEX + BANK_CODE_LENGTH);
    }

    private static String getCountryCode(final String bic) {
        return bic.substring(COUNTRY_CODE_INDEX, COUNTRY_CODE_INDEX + COUNTRY_CODE_LENGTH);
    }

    private static String getLocationCode(final String bic) {
        return bic.substring(LOCATION_CODE_INDEX, LOCATION_CODE_INDEX + LOCATION_CODE_LENGTH);
    }

    private static String getBranchCode(final String bic) {
        return bic.substring(BRANCH_CODE_INDEX, BRANCH_CODE_INDEX + BRANCH_CODE_LENGTH);
    }

    private static boolean hasBranchCode(final String bic) {
        return bic.length() == BIC11_LENGTH;
    }
}
