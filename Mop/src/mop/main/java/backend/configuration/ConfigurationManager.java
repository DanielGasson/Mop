package mop.main.java.backend.configuration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.XmlException;
import mop.main.java.backend.utilities.DomUtilities;
import mop.main.java.backend.utilities.Log;
import mop.main.java.backend.utilities.XmlDocument;

import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ConfigurationManager {

    private static final Logger log = Log.getLog(ConfigurationManager.class);

    private Transformer transformer;
    private XmlDocument documentBuilder;
    private final DomUtilities domUtilities;

    public ConfigurationManager() throws XmlException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        try {

            documentBuilder = new XmlDocument();
            this.setFormattingOptions(transformerFactory);
        }
        catch (ParserConfigurationException ex) {

            log.error("Failed to initialise document builder. Unable to continue.", ex);
            throw new XmlException(ex.getMessage());
        }
        catch (TransformerConfigurationException ex) {

            log.error("Unable to configure xml transformer. Unable to continue.", ex);
            throw new XmlException(ex.getMessage());
        }

        domUtilities = new DomUtilities();
    }

    /**
     * Creates a new, empty configuration file.
     * @return true if the config is successfully created.
     */
    public boolean createConfiguration(String configUrl) {

        boolean isSuccessful = false;

        if(mop.main.java.backend.utilities.File.exists(configUrl)) {

            log.warn("App config already exists. Creating another config will overwrite this existing config.");
            return false;
        }

        DOMSource xml = this.createEmptyConfiguration();

        if(xml == null) {

            return false;
        }

        StreamResult config = new StreamResult(new File(configUrl));

        try {

            transformer.transform(xml, config);

            isSuccessful = true;
        }
        catch (TransformerException ex) {

            log.error("Error creating xml file.", ex);
        }

        return isSuccessful;
    }

    /**
     * Gets all app configuration values.
     * @return an ordered Map of configuration keys and their corresponding values.
     */
    public AppConfig getAllValues(String configUrl) {

        File config = mop.main.java.backend.utilities.File.tryGet(configUrl);
        if(config == null) {

            log.warn("App config does not exist. Unable to retrieve config values.");
            return null;
        }

        Document document;
        try {

            document = documentBuilder.buildDocument(config);
        }
        catch(SAXException | IOException ex) {

            return null;
        }

        Element element = document.getDocumentElement();
        element.normalize();
        LinkedHashMap<String, String> values = domUtilities.getAllNodeValues(element);

        return new AppConfig.Builder()
            .username(values.get(Constants.AppConfigSections.username))
            .password(values.get(Constants.AppConfigSections.password))
            .defaultSchema(values.get(Constants.AppConfigSections.defaultSchema))
            .id(values.get(Constants.AppConfigSections.id))
            .name(values.get(Constants.AppConfigSections.name))
            .build();
    }

    /**
     * Writes a new value to the app config.
     * @param key, the config node to write to.
     * @param value, the value to write.
     * @return true if the value is successfully written.
     */
    public boolean setValue(String key, String value, String configUrl) {

        boolean isSuccessful = false;

        File config = mop.main.java.backend.utilities.File.tryGet(configUrl);
        if(config == null) {

            log.error("App config does not exist. Unable to write new value.");
            return false;
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(config);

            document.getDocumentElement().normalize();

            if(!domUtilities.setNodeValue(key, value, document, null)){

                return false;
            }

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(configUrl));
            transformer.transform(source, result);

            isSuccessful = true;
        }
        catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {

            log.error("Error writing new value to app config.", ex);
        }

        return isSuccessful;
    }

    private void setFormattingOptions(TransformerFactory transformerFactory) throws TransformerConfigurationException {

        transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
    }

    private DOMSource createEmptyConfiguration() {

        Document document;

        try {

            document = documentBuilder.buildDocument(null);
        }
        catch (SAXException | IOException ex) {

            log.error("Error building document.", ex);
            return null;
        }

        // root configuration
        Element configuration = domUtilities.addRootNode(document, "configuration");

        // database
        Element database = domUtilities.addChildNode(document, "database", configuration, null);
        domUtilities.addAttribute(document, "vendor", database, null);
        domUtilities.addChildNode(document, "username", database, null);
        domUtilities.addChildNode(document, "password", database, null);
        domUtilities.addChildNode(document, "defaultSchema", database, null);

        // playlist
        Element playlist = domUtilities.addChildNode(document, "playlist", configuration, null);
        Element current = domUtilities.addChildNode(document, "current", playlist, null);
        domUtilities.addChildNode(document, "id", current, null);
        domUtilities.addChildNode(document, "name", current, null);

        // settings
        domUtilities.addChildNode(document, "settings", configuration, null);

        return new DOMSource(document);
    }
}
