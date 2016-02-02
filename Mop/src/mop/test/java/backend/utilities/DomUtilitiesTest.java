package mop.test.java.backend.utilities;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import static org.junit.Assert.assertEquals;

import mop.main.java.backend.utilities.DomUtilities;

public class DomUtilitiesTest {

    private DocumentBuilder documentBuilder;
    private Document document;

    public DomUtilitiesTest() throws ParserConfigurationException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
    }

    @Before
    public void setup() throws SAXException, IOException {

        document = documentBuilder.parse(new File("src/mop/test/resources/ReadonlyTestConfig.xml"));
    }

    @After
    public void teardown() {

        document = null;
    }

    @Test
    public void testGetAllNodeValues() {

        // Arrange
        DomUtilities sut = new DomUtilities();
        Element rootNode = document.getDocumentElement();

        // Act
        LinkedHashMap<String, String> result = sut.getAllNodeValues(rootNode);

        // Assert
        assertEquals(10, result.entrySet().size());
        assertEquals("", result.get("configuration"));
        assertEquals("", result.get("database"));
        assertEquals("user", result.get("username"));
        assertEquals("password", result.get("password"));
        assertEquals("schema", result.get("defaultSchema"));
        assertEquals("", result.get("playlist"));
        assertEquals("", result.get("current"));
        assertEquals("2", result.get("id"));
        assertEquals("default", result.get("name"));
        assertEquals("", result.get("settings"));
    }
}
