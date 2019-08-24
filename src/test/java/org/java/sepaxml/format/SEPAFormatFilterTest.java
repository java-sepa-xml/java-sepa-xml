package org.java.sepaxml.format;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SEPAFormatFilterTest {

    @Test
    public void filter() throws Exception {
        assertEquals("NL09ABNA3966322153", SEPAFormatFilter.filter("NL09ABNA3966322153"));
        assertEquals("DK2950511129328943", SEPAFormatFilter.filter(" DK 295 05111 2932 8943 "));
        assertEquals("BE98561629126693", SEPAFormatFilter.filter("BE!-#'+*~`ß\\?=}][]{¬{½&%$§98561629126693"));
        assertEquals("AT022011100003429660", SEPAFormatFilter.filter("#AT022011100003429660"));
        assertEquals("FR9730066281591839552289646", SEPAFormatFilter.filter("FR9730066281591839552289646"));

    }

    @Test
    public void filterBIC() throws Exception {
        assertEquals("BKCHPLPXXXX", SEPAFormatFilter.filterBIC("BKCHPLP"));
        assertEquals("EBOSPLPW017", SEPAFormatFilter.filterBIC("EBOSPLPW017"));
        assertEquals("CITIPLPXXXX", SEPAFormatFilter.filterBIC("C!I_-?#+´'TIPLPXXXX"));
        assertEquals("KRDBPLPWPOB", SEPAFormatFilter.filterBIC(" KRDBP LPW PO B "));
    }
}