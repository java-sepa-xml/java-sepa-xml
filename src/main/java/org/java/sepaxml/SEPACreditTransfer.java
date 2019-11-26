package org.java.sepaxml;

import org.java.sepaxml.xml.XMLNode;
import org.java.sepaxml.xml.XMLNode;

import java.util.Date;
import java.util.List;

public class SEPACreditTransfer extends SEPA {

    public SEPACreditTransfer(SEPABankAccount sender, List<SEPATransaction> transactions) {
        this(sender, transactions, new Date());
    }

    public SEPACreditTransfer(SEPABankAccount sender, List<SEPATransaction> transactions, Date executionDate) {
        super(sender, transactions, executionDate);
        this.build();
    }

    @Override
    protected String getType() {
        return "Db";
    }

    @Override
    protected void addTransactions() {
        for (SEPATransaction transaction : this.transactions) {
            XMLNode nodeCdtTrfTxInf = this.nodePmtInf.append("CdtTrfTxInf");

            nodeCdtTrfTxInf.append("PmtId")
                    .append("EndToEndId").value("NOTPROVIDED");

            nodeCdtTrfTxInf.append("Amt").
                    append("InstdAmt")
                    .attr("Ccy", transaction.getCurrency().toString())
                    .value(transaction.getValue().doubleValue());

            nodeCdtTrfTxInf.append("CdtrAgt")
                    .append("FinInstnId").append("BIC")
                    .value(transaction.getBankAccount().getBIC());

            nodeCdtTrfTxInf.append("Cdtr")
                    .append("Nm")
                    .value(transaction.getBankAccount().getName());

            nodeCdtTrfTxInf.append("CdtrAcct")
                    .append("Id").append("IBAN")
                    .value(transaction.getBankAccount().getIBAN());

            nodeCdtTrfTxInf.append("RmtInf")
                    .append("Ustrd")
                    .value(transaction.getSubject());

            if (transaction.getRemittance() != null) {
                nodeCdtTrfTxInf.append("RmtInf")
                        .append("Ustrd")
                        .value(transaction.getRemittance());
            } else {
                nodeCdtTrfTxInf.append("RmtInf")
                        .append("Ustrd")
                        .value(transaction.getSubject());
            }
        }
    }
}
