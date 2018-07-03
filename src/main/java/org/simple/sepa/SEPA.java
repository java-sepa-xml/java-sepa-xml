package org.simple.sepa;

import org.simple.sepa.format.SEPAFormatDate;
import org.simple.sepa.xml.XMLNode;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public abstract class SEPA {

    protected SEPABankAccount reciver;
    protected List<SEPATransaction> transactions;

    protected Date executionDate;

    protected XMLNode document;
    protected XMLNode nodePmtInf;

    public SEPA(SEPABankAccount reciver, List<SEPATransaction> transactions) {
        this(reciver, transactions, new Date());
    }

    public SEPA(SEPABankAccount reciver, List<SEPATransaction> transactions, Date executionDate) {
        this.reciver = reciver;
        this.transactions = transactions;
        this.executionDate = executionDate;
    }

    protected void build() {
        this.document = new XMLNode().append("Document")
                .attr("xmlns", "urn:iso:std:iso:20022:tech:xsd:pain.008.002.02")
                .attr("xsi:schemaLocation", "urn:iso:std:iso:20022:tech:xsd:pain.008.002.02 pain.008.002.02.xsd")
                .attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");

        XMLNode nodeCstmrDrctDbtInitn = this.document.append("CstmrDrctDbtInitn");
        XMLNode nodeGrpHdr = nodeCstmrDrctDbtInitn.append("GrpHdr");

        nodeGrpHdr.append("MsgId").value(this.reciver.getBIC() + "00" + SEPAFormatDate.formatDate(executionDate));
        nodeGrpHdr.append("CreDtTm").value(SEPAFormatDate.formatDateLong(executionDate));
        nodeGrpHdr.append("NbOfTxs").value(this.transactions.size());
        nodeGrpHdr.append("InitgPty").append("Nm").value(this.reciver.getName());

        this.nodePmtInf = nodeCstmrDrctDbtInitn.append("PmtInf");
        this.nodePmtInf.append("PmtInfId").value("PMT-ID0-" + SEPAFormatDate.formatDate(executionDate));
        this.nodePmtInf.append("PmtMtd").value("DD");
        this.nodePmtInf.append("BtchBookg").value("true");
        this.nodePmtInf.append("NbOfTxs").value(this.transactions.size());
        this.nodePmtInf.append("CtrlSum").value(this.getTransactionVolume());

        XMLNode nodePmtTpInf = this.nodePmtInf.append("PmtTpInf");
        nodePmtTpInf.append("SvcLvl").append("Cd").value("SEPA");
        nodePmtTpInf.append("LclInstrm").append("Cd").value("CORE");
        nodePmtTpInf.append("SeqTp").append("Cd").value("FRST");

        this.nodePmtInf.append("ReqdColltnDt").value(SEPAFormatDate.formatDateShort(executionDate));
        this.nodePmtInf.append(this.getType() + "tr")
                .append("Nm").value(this.reciver.getName());

        this.nodePmtInf.append(this.getType() + "trAcct")
                .append("Id")
                .append("IBAN")
                .value(this.reciver.getIBAN());

        this.nodePmtInf.append(this.getType() + "trAgt")
                .append("FinInstnId")
                .append("BIC")
                .value(this.reciver.getBIC());

        this.nodePmtInf.append("ChrgBr").value("SLEV");

        this.addTransactions();
    }

    protected abstract String getType();

    protected abstract void addTransactions();

    private double getTransactionVolume() {
        double volume = 0.0d;
        for (SEPATransaction transaction : this.transactions) {
            volume += transaction.getValue();
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
