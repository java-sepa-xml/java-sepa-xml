package org.simple.sepa.validator;

import org.simple.sepa.format.SEPAFormatFilter;

import java.util.HashMap;
import java.util.Map;

public class SEPAValidatorIBAN {

    private static final Map<String, Integer> CODE_LENGTHS = new HashMap<String, Integer>() {{
        put("AD", 24);
        put("AE", 23);
        put("AT", 20);
        put("AZ", 28);
        put("BA", 20);
        put("BE", 16);
        put("BG", 22);
        put("BH", 22);
        put("BR", 29);
        put("CH", 21);
        put("CR", 21);
        put("CY", 28);
        put("CZ", 24);
        put("DE", 22);
        put("DK", 18);
        put("DO", 28);
        put("EE", 20);
        put("ES", 24);
        put("FI", 18);
        put("FO", 18);
        put("FR", 27);
        put("GB", 22);
        put("GI", 23);
        put("GL", 18);
        put("GR", 27);
        put("GT", 28);
        put("HR", 21);
        put("HU", 28);
        put("IE", 22);
        put("IL", 23);
        put("IS", 26);
        put("IT", 27);
        put("JO", 30);
        put("KW", 30);
        put("KZ", 20);
        put("LB", 28);
        put("LI", 21);
        put("LT", 20);
        put("LU", 20);
        put("LV", 21);
        put("MC", 27);
        put("MD", 24);
        put("ME", 22);
        put("MK", 19);
        put("MR", 27);
        put("MT", 31);
        put("MU", 30);
        put("NL", 18);
        put("NO", 15);
        put("PK", 24);
        put("PL", 28);
        put("PS", 29);
        put("PT", 25);
        put("QA", 29);
        put("RO", 24);
        put("RS", 22);
        put("SA", 24);
        put("SE", 24);
        put("SI", 19);
        put("SK", 24);
        put("SM", 27);
        put("TN", 24);
        put("TR", 26);
    }};

    public static boolean isValid(String input) {
        if (input == null) {
            return false;
        }

        // filter
        String iban = SEPAFormatFilter.filter(input);

        // check length based on country code
        String contryCode;
        if (iban.length() > 2) {
            contryCode = iban.substring(0, 2);
            Integer expectedLength = CODE_LENGTHS.get(contryCode);
            if (expectedLength == null || iban.length() != expectedLength) {
                return false;
            }
        } else {
            return false;
        }

        String checkSum = iban.substring(2, 4);
        String number = iban.substring(4);
        String digist = number + contryCode + checkSum;

        StringBuilder digitsBuilder = new StringBuilder();
        for (char c : digist.toUpperCase().toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                digitsBuilder.append(c - 55);
            } else {
                digitsBuilder.append(c);
            }
        }

        return mod97(digitsBuilder.toString()) == 1;
    }

    private static int mod97(String digits) {
        String checksum = digits.substring(0, 2);
        String fragment;

        for (int offset = 2; offset < digits.length(); offset += 7) {
            int end = offset + 7;
            if (end >= digits.length()) {
                end = digits.length();
            }

            fragment = checksum + digits.substring(offset, end);
            checksum = Integer.toString(Integer.valueOf(fragment, 10) % 97);
        }

        return Integer.valueOf(checksum);
    }
}
