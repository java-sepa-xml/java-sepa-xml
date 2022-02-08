package org.java.sepaxml.examples;

import org.java.sepaxml.SEPA;
import org.java.sepaxml.SEPABankAccount;
import org.java.sepaxml.SEPADirectDebit;
import org.java.sepaxml.SEPATransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExampleDirectDebit {
    public static void main(String[] args) {
        final String creditorID = "DE98ZZZ09999999999";

        final SEPABankAccount reciver = new SEPABankAccount(
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
                            new BigDecimal(10.20d),
                            "Invoice 4711564",
                            new Date(),
                            "MYCOMP11111111",
                            new Date(),
                            SEPATransaction.Currency.EUR,
                            "No Information"
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "DK5250511963137134",
                                    "UINVDEFFXXX",
                                    "Carl White"
                            ),
                            new BigDecimal(1000.00d),
                            "Invoice 789765",
                            new Date(),
                            "MYCOMP22222222",
                            new Date(),
                            SEPATransaction.Currency.EUR,
                            "No Information"
                    )
            );

            add(new SEPATransaction(
                            new SEPABankAccount(
                                    "CZ7627005991764514418145",
                                    "SWBSDESSXXX",
                                    "Frank Black"
                            ),
                            new BigDecimal(50.00d),
                            "Invoice 7856",
                            new Date(),
                            "MYCOMP11111111",
                            new Date(),
                            SEPATransaction.Currency.EUR,
                            "No Information"
                    )
            );
        }};

        final SEPA sepa = new SEPADirectDebit(SEPA.PaymentMethods.TransferAdvice, reciver, transactions, creditorID);
        System.out.println(sepa.toString());
    }
}