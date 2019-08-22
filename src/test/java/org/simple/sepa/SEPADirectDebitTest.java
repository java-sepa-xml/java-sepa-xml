package org.simple.sepa;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SEPADirectDebitTest {

    @Test
    public void transactionVolume() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        final SEPABankAccount reciver = new SEPABankAccount(
                "DE80 3007 0010 0122 1902 00",
                "DEUTDEDDXXX",
                "Deutsche Bank AG Düsseldorf"
        );


        List<SEPATransaction> transactions = new LinkedList<SEPATransaction>();
        for (double value : Arrays.asList(100, 200, 300)) {
            transactions.add(new SEPATransaction(reciver,
                    BigDecimal.valueOf(value),
                    "Test Transaction Volume",
                    new Date(),
                    "TTV",
                    new Date(),
                    SEPATransaction.Currency.EUR,
                    null));
        }

        SEPADirectDebit test = new SEPADirectDebit(reciver, transactions, "Test");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        test.write(out);

        Document read = read(new ByteArrayInputStream(out.toByteArray()));

        XPath xPath = XPathFactory.newInstance().newXPath();
        Double evaluate = (Double) xPath.compile("/Document/CstmrCdtTrfInitn/GrpHdr/CtrlSum").evaluate(read, XPathConstants.NUMBER);
        assertEquals((Double) 600.0, evaluate);
    }

    private Document read(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(inputStream));
    }

}