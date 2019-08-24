package org.java.sepaxml;

import org.java.sepaxml.xml.XMLNode;
import org.java.sepaxml.format.SEPAFormatDate;
import org.java.sepaxml.xml.XMLNode;

import java.util.Date;
import java.util.List;

public class SEPADirectDebit extends SEPA {

    private String creditorID;

    public SEPADirectDebit(SEPABankAccount reciever, List<SEPATransaction> transactions, String creditorID) {
        this(reciever, transactions, new Date(), creditorID);
    }

    public SEPADirectDebit(SEPABankAccount reciever, List<SEPATransaction> transactions, Date executionDate, String creditorID) {
        super(reciever, transactions, executionDate);
        this.creditorID = creditorID;
        this.build();
    }

    @Override
    protected String getType() {
        return "Cd";
    }

    @Override
    protected void addTransactions() {
        XMLNode nodeOthr = this.nodePmtInf.append("CdtrSchmeId")
                .append("Id")
                .append("PrvtId")
                .append("Othr");

        nodeOthr.append("Id").value(this.creditorID);
        nodeOthr.append("SchmeNm")
                .append("Prtry")
                .value("SEPA");

        for (SEPATransaction transaction : this.transactions) {
            XMLNode nodeDrctDbtTxInf = this.nodePmtInf.append("DrctDbtTxInf");

            XMLNode pmtId = nodeDrctDbtTxInf.append("PmtId");
            if (transaction.getId() != null) {
                pmtId.append("InstrId").value(transaction.getId());
            }
            pmtId.append("EndToEndId").value(transaction.getEndToEndId() != null
                    ? transaction.getEndToEndId()
                    : "NOTPROVIDED");

            nodeDrctDbtTxInf.append("Amt").
                    append("InstdAmt")
                    .attr("Ccy", transaction.getCurrency().toString())
                    .value(transaction.getValue().doubleValue());

            XMLNode nodeMndtRltdInf = nodeDrctDbtTxInf.append("DrctDbtTx")
                    .append("MndtRltdInf");

            nodeMndtRltdInf.append("MndtId")
                    .value(transaction.getMandatReference());

            nodeMndtRltdInf.append("DtOfSgntr")
                    .value(SEPAFormatDate.formatDateShort(transaction.getMandatReferenceDate()));

            nodeMndtRltdInf.append("AmdmntInd")
                    .value("false");

            nodeDrctDbtTxInf.append("DbtrAgt").
                            append("FinInstnId")
                            .append("BIC")
                            .value(transaction.getBankAccount().getBIC());

            nodeDrctDbtTxInf.append("Dbtr")
                    .append("Nm")
                    .value(transaction.getBankAccount().getName());

            nodeDrctDbtTxInf.append("DbtrAcct").
                    append("Id")
                    .append("IBAN")
                    .value(transaction.getBankAccount().getIBAN());

            nodeDrctDbtTxInf.append("UltmtDbtr")
                    .append("Nm")
                    .value(transaction.getBankAccount().getName());

            nodeDrctDbtTxInf.append("RmtInf")
                    .append("Ustrd")
                    .value(transaction.getSubject());
        }
    }
}