package org.simple.sepa.examples;

import org.simple.sepa.SEPA;
import org.simple.sepa.SEPABankAccount;
import org.simple.sepa.SEPACreditTransfer;
import org.simple.sepa.SEPATransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExampleCreditTransfer {
    public static void main(String[] args) {
        final SEPABankAccount sender = new SEPABankAccount(
                "DE89370400440532013000",
                "DEUTDEBBXXX",
                "Joe Doe"
        );

        final List<SEPATransaction> transactions = new ArrayList<SEPATransaction>() {{
            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DE05500105173195282731",
                                    "VBRSDE33347",
                                    "Peter Parker"
                            ),
                            new BigDecimal(2000.20d),
                            "Salary PP 2018-07",
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DK5250511963137134",
                                    "UINVDEFFXXX",
                                    "Carl White"
                            ),
                            new BigDecimal(1000.00d),
                            "Salary CW 2015-12",
                            SEPATransaction.Currency.EUR
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "CZ7627005991764514418145",
                                    "SWBSDESSXXX",
                                    "Frank Black"
                            ),
                            new BigDecimal(5000.00d),
                            "Salary FB 2017-05",
                            SEPATransaction.Currency.EUR
                    )
            );
        }};

        final SEPA sepa = new SEPACreditTransfer(sender, transactions);
        sepa.write(System.out);
    }
}
