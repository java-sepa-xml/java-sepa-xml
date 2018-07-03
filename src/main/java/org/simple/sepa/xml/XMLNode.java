package org.simple.sepa.xml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class XMLNode {

    private XMLNode parent;
    private Document document;
    private Element node;

    public XMLNode() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.document = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public XMLNode(XMLNode parent, Document document, Element childNode) {
        this.parent = parent;
        this.document = document;
        this.node = childNode;
    }

    public XMLNode append(String name) {
        Element childElement = this.document.createElement(name);

        if (this.node == null) {
            this.document.appendChild(childElement);
        } else {
            this.node.appendChild(childElement);
        }

        XMLNode childNode = new XMLNode(
                this,
                this.document,
                childElement
        );

        return childNode;
    }

    public XMLNode attr(String key, String value) {
        Attr attr = this.document.createAttribute(key);
        attr.setValue(value);
        this.node.setAttributeNode(attr);
        return this;
    }

    public XMLNode value(String value) {
        this.node.appendChild(this.document.createTextNode(value));
        return this;
    }

    public XMLNode value(int value) {
        value(Integer.toString(value));
        return this;
    }

    public XMLNode value(double value) {
        value(Double.toString(value));
        return this;
    }

    public void write(OutputStream out) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(this.document);

            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            write(baos);
            return baos.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
