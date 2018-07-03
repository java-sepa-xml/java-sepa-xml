package org.simple.sepa.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SEPAValidatorIBANTest {
    @Test
    public void isValidValidIBANS() throws Exception {
        assertTrue(new SEPAValidatorIBAN().isValid("NL56INGB0758031605"));
        assertTrue(new SEPAValidatorIBAN().isValid("NL84RABO0174624636"));
        assertTrue(new SEPAValidatorIBAN().isValid("FR9330066688265777237545375"));
        assertTrue(new SEPAValidatorIBAN().isValid("FR5630066375266881733924552"));
        assertTrue(new SEPAValidatorIBAN().isValid("FR8330066916797896982199839"));
        assertTrue(new SEPAValidatorIBAN().isValid("DE79500105179714857427"));
        assertTrue(new SEPAValidatorIBAN().isValid("DE41500105179952898827"));
        assertTrue(new SEPAValidatorIBAN().isValid("DE51500105179318132975"));
        assertTrue(new SEPAValidatorIBAN().isValid("GR9601473213859855758968625"));
        assertTrue(new SEPAValidatorIBAN().isValid("GR2601734482571319338177392"));
        assertTrue(new SEPAValidatorIBAN().isValid("LI3808800722131199182"));
        assertTrue(new SEPAValidatorIBAN().isValid("LI2608800236927391728"));
        assertTrue(new SEPAValidatorIBAN().isValid("MT53YAQV16648834352644216379893"));
        assertTrue(new SEPAValidatorIBAN().isValid("MC4612739269895716528321535"));
        assertTrue(new SEPAValidatorIBAN().isValid("MC7212739225729729174753489"));
        assertTrue(new SEPAValidatorIBAN().isValid("ES9401825773892913164985"));
        assertTrue(new SEPAValidatorIBAN().isValid("ES8814656129561178423214"));
        assertTrue(new SEPAValidatorIBAN().isValid("CH7148768265676952958"));
        assertTrue(new SEPAValidatorIBAN().isValid("CH3445368738755825564"));
        assertTrue(new SEPAValidatorIBAN().isValid("CH6231251364979949952"));
        assertTrue(new SEPAValidatorIBAN().isValid("SE8766833397923616914696"));
        assertTrue(new SEPAValidatorIBAN().isValid("SE3175485823846176929678"));
        assertTrue(new SEPAValidatorIBAN().isValid("SE3097157774957185416549"));
    }

    @Test
    public void isValidInvalidIBANS() throws Exception {
        assertFalse(new SEPAValidatorIBAN().isValid("CH7148768265176952958"));
        assertFalse(new SEPAValidatorIBAN().isValid("CH3445768738755825564"));
        assertFalse(new SEPAValidatorIBAN().isValid("CH6231251364979989952"));
        assertFalse(new SEPAValidatorIBAN().isValid("SE8766833397923626914696"));
        assertFalse(new SEPAValidatorIBAN().isValid("SE3175485823866176929678"));
        assertFalse(new SEPAValidatorIBAN().isValid("SE3097157774987185416549"));
    }
}