package org.java.sepaxml;

import org.java.sepaxml.xml.XMLNode;
import org.java.sepaxml.format.SEPAFormatDate;
import org.java.sepaxml.xml.XMLNode;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class SEPA {
    public enum PaymentMethods {

        Cheque("CHK"), TransferAdvice("TRF"),  CreditTransfer("TRA");

        private final String code;

        PaymentMethods(String code) {
            this.code = code;
        }
    }

    protected SEPABankAccount reciver;
    protected List<SEPATransaction> transactions;

    protected Date executionDate;

    protected XMLNode document;
    protected XMLNode nodePmtInf;

    protected PaymentMethods paymentMethod = PaymentMethods.CreditTransfer;

    public SEPA(PaymentMethods paymentMethod, SEPABankAccount reciver, List<SEPATransaction> transactions) {
        this(paymentMethod, reciver, transactions, new Date());
    }

    public SEPA(PaymentMethods paymentMethod, SEPABankAccount reciver, List<SEPATransaction> transactions, Date executionDate) {
        this.paymentMethod = paymentMethod;
        this.reciver = reciver;
        this.transactions = transactions;
        this.executionDate = executionDate;
    }

    protected void build() {
        this.document = new XMLNode().append("Document")
                .attr("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.03")
                .attr("xsi:schemaLocation", "urn:iso:std:iso:20022:tech:xsd:pain.001.001.03 pain.001.001.03.xsd")
                .attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        XMLNode nodeCstmrDrctDbtInitn = this.document.append("CstmrCdtTrfInitn");
        XMLNode nodeGrpHdr = nodeCstmrDrctDbtInitn.append("GrpHdr");

        nodeGrpHdr.append("MsgId").value(this.reciver.getBIC() + "00" + SEPAFormatDate.formatDate(executionDate));
        nodeGrpHdr.append("CreDtTm").value(SEPAFormatDate.formatDateLong(executionDate));
        nodeGrpHdr.append("NbOfTxs").value(this.transactions.size());
        nodeGrpHdr.append("CtrlSum").value(this.getTransactionVolume().doubleValue());
        nodeGrpHdr.append("InitgPty").append("Nm").value(this.reciver.getName());

        this.nodePmtInf = nodeCstmrDrctDbtInitn.append("PmtInf");
        this.nodePmtInf.append("PmtInfId").value("PMT-ID0-" + SEPAFormatDate.formatDate(executionDate));
        this.nodePmtInf.append("PmtMtd").value(paymentMethod.code); // For PAIN 001 (Überweisung) there are three Payment Methods: CHK (Cheque), TRF (TransferAdvice), TRA (CreditTransfer)
        this.nodePmtInf.append("BtchBookg").value("true");
        this.nodePmtInf.append("NbOfTxs").value(this.transactions.size());
        this.nodePmtInf.append("CtrlSum").value(this.getTransactionVolume().doubleValue());

        XMLNode nodePmtTpInf = this.nodePmtInf.append("PmtTpInf");
        nodePmtTpInf.append("SvcLvl").append("Cd").value("SEPA");
        // nodePmtTpInf.append("LclInstrm").append("Cd").value("CORE"); // only necessary for PAIN 008 (Lastschrift)
        // nodePmtTpInf.append("SeqTp").append("Cd").value("FRST"); // only necessary for PAIN 008 (Lastschrift)

        this.nodePmtInf.append("ReqdExctnDt").value(SEPAFormatDate.formatDateShort(executionDate));
        this.nodePmtInf.append(this.getType() + "tr")
                .append("Nm").value(this.reciver.getName());

        this.nodePmtInf.append(this.getType() + "trAcct")
                .append("Id")
                .append("IBAN")
                .value(this.reciver.getIBAN());

        if (this.reciver.getBIC() != null) {
            this.nodePmtInf.append(this.getType() + "trAgt")
                    .append("FinInstnId")
                    .append("BIC")
                    .value(this.reciver.getBIC());
        }

        this.nodePmtInf.append("ChrgBr").value("SLEV");

        this.addTransactions();
    }

    protected abstract String getType();

    protected abstract void addTransactions();

    private BigDecimal getTransactionVolume() {
        BigDecimal volume = BigDecimal.ZERO;
        for (SEPATransaction transaction : this.transactions) {
            volume = volume.add(transaction.getValue());
        }
        return volume;
    }

    public void write(OutputStream outputStream) {
        this.document.write(outputStream);
    }

    public String toString() {
        return this.document.toString();
    }
}
