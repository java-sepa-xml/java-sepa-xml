package org.java.sepaxml.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SEPAValidatorBICTest {

    @Test
    public void isValid() throws Exception {
        assertTrue(new SEPAValidatorBIC().isValid("ABNADEFFKOE"));
        assertTrue(new SEPAValidatorBIC().isValid("AGIDDEFFIAM"));
        assertTrue(new SEPAValidatorBIC().isValid("BFSWDE33KRL"));
        assertTrue(new SEPAValidatorBIC().isValid("BABEDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("BSIRDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("BARCDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("FLESDEMM840"));
        assertTrue(new SEPAValidatorBIC().isValid("BAYVDEMMXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("CMCIDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("DAKVDEFFA16"));
        assertTrue(new SEPAValidatorBIC().isValid("DAKVDEFFONF"));
        assertTrue(new SEPAValidatorBIC().isValid("DPFADEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("ESBKDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("GENODEFFBSC"));
        assertTrue(new SEPAValidatorBIC().isValid("GENODEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("GENODESGXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("EMPODEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("HYESDE3EXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("HAUKDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("ISBKDEFXMAN"));
        assertTrue(new SEPAValidatorBIC().isValid("JYBADEHHXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("GOPSDE6GXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("OBKLDEMXWEI"));
        assertTrue(new SEPAValidatorBIC().isValid("RHENDE3HXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("SOLADEST850"));
        assertTrue(new SEPAValidatorBIC().isValid("SOLADEST802"));
        assertTrue(new SEPAValidatorBIC().isValid("SIKADEMMXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("SOGEDEFFCBF"));
        assertTrue(new SEPAValidatorBIC().isValid("WELADE3HXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("PZHSDE66NAG"));
        assertTrue(new SEPAValidatorBIC().isValid("YUINDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("UINVDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("VBRTDE6RWWB"));
        assertTrue(new SEPAValidatorBIC().isValid("VONEDE33STG"));
        assertTrue(new SEPAValidatorBIC().isValid("TCZBDEFFXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("ZGIEDEFFXXX"));

        assertTrue(new SEPAValidatorBIC().isValid("EBOSPLPW290"));
        assertFalse(new SEPAValidatorBIC().isValid("EOSPLPW290"));
        assertTrue(new SEPAValidatorBIC().isValid("PKOPPLPWKRA"));
        assertFalse(new SEPAValidatorBIC().isValid("KOPPLPWKRA"));
        assertTrue(new SEPAValidatorBIC().isValid("POLUPLPRXXX"));
        assertTrue(new SEPAValidatorBIC().isValid("POLUPLPR"));
    }

}