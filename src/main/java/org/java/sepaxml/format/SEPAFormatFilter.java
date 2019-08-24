package org.java.sepaxml.format;

public class SEPAFormatFilter {

    /**
     * Returns a filtered string which only contains numbers and letters.
     *
     * @param filter The string which should be filtered
     * @return The filtered string
     */
    public static String filter(String filter) {
        StringBuilder output = new StringBuilder();
        for (char c : filter.toCharArray()) {
            if (c >= '0' && c <= '9'
                || c >= 'a' && c <= 'z'
                || c >= 'A' && c <= 'Z'
            ) {
                output.append(c);
            }
        }
        return output.toString();
    }

    /**
     * Returns a filtered BIC which only contains numbers or letters.
     * If the size of the filtered BIC is under 11 a padding of one or multiple 'X' will be added at the end of the string.
     *
     * @param bic The BIC which should be filtered
     * @return The filtered BIC
     */
    public static String filterBIC(String bic) {
        StringBuilder bicBuilder = new StringBuilder(filter(bic));
        while (bicBuilder.length() < 11) {
            bicBuilder.append("X");
        }
        return bicBuilder.toString();
    }
}