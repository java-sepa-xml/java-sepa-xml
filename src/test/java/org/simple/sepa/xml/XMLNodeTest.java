package org.simple.sepa.xml;

import org.junit.Test;

import static org.junit.Assert.*;

public class XMLNodeTest {

    @Test
    public void appendSingleNode() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><cars><car/></cars>";
        assertEquals(xml,
                new XMLNode().append("cars").append("car").toString()
        );
    }

    @Test
    public void appendMultipleNodes() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><cars><car>Ferrari</car><car>Lamborghini</car><car>Audi</car><car>BMW</car><car>Mercedes</car></cars>";

        XMLNode cars = new XMLNode().append("cars");

        cars.append("car").value("Ferrari");
        cars.append("car").value("Lamborghini");
        cars.append("car").value("Audi");
        cars.append("car").value("BMW");
        cars.append("car").value("Mercedes");

        assertEquals(xml, cars.toString());
    }

    @Test
    public void attrSingle() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><car ps=\"150\"/>";

        XMLNode node = new XMLNode().append("car").attr("ps", "150");

        assertEquals(xml, node.toString());
    }

    @Test
    public void attrMultiple() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><car ps=\"150\" transmission=\"5\"/>";

        XMLNode node = new XMLNode().append("car").attr("ps", "150").attr("transmission", "5");

        assertEquals(xml, node.toString());
    }

    @Test
    public void value() {
        final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><car>Great Car!</car>";

        XMLNode node = new XMLNode().append("car").value("Great Car!");

        assertEquals(xml, node.toString());
    }
}