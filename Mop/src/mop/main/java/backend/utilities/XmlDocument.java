package mop.main.java.backend.utilities;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDocument {

    private DocumentBuilder documentBuilder;

    public XmlDocument() throws ParserConfigurationException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(false);
        builderFactory.setValidating(false);

        documentBuilder = builderFactory.newDocumentBuilder();
    }

    /**
     * Builds a Document from a File, or creates a new empty Document if file supplied is null.
     * @param file, the file to create the document from
     * @return a Document created from the file, or a new Document if the file is null
     * @throws SAXException
     * @throws IOException
     */
    public Document buildDocument(File file) throws SAXException, IOException {

        return file == null
            ? documentBuilder.newDocument()
            : documentBuilder.parse(file);
    }
}
