package mop.main.java.backend.utilities;

import java.util.LinkedHashMap;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomUtilities {

    /**
     * Sets the value of a DOM node.
     * @param key - the name of the node to set.
     * @param value - the value to set on the node.
     * @param document - the DOM to traverse for the node.
     * @param nodeItem - the (indexed) item in the node to edit.
     * If this parameter isn't set it will default to the first item in the node.
     * @return true if the value is set, false if the current node can't be retrieved.
     */
    public boolean setNodeValue(String key, String value, Document document, Integer nodeItem) {

        if(nodeItem == null) {

            nodeItem = 0;
        }

        Node currentNode = document.getElementsByTagName(key).item(nodeItem);
        if(currentNode != null) {

            currentNode.setTextContent(value);
            return true;
        }

        return false;
    }

    /**
     * Creates a root node.
     * @param document - the document to create the root node in.
     * @param nodeName - the name of the root node.
     * @return the root node element.
     */
    public Element addRootNode(Document document, String nodeName) {

        Element root = document.createElement(nodeName);
        document.appendChild(root);
        return root;
    }

    /**
     * Creates a child node.
     * @param document - the document to create the node in.
     * @param nodeName - the name of the node.
     * @param parentNode - the parent node for this node to sit inside.
     * @param value - an optional initial value to assign to the node.
     * @return the node element.
     */
    public Element addChildNode(Document document, String nodeName, Element parentNode, String value) {

        Element element = document.createElement(nodeName);

        if(value != null) {

            element.appendChild(document.createTextNode(value));
        }

        parentNode.appendChild(element);
        return element;
    }

    /**
     * Creates a node attribute.
     * @param document - the document to create the attribute in.
     * @param attributeName - the name of the attribute.
     * @param node - the node to add the attribute to.
     * @param value - an optional initial value to assign to the node attribute.
     * @return the node attribute.
     */
    public Attr addAttribute(Document document, String attributeName, Element node, String value) {

        Attr attribute = document.createAttribute(attributeName);

        if(value != null) {

            attribute.setValue(value);
        }

        node.setAttributeNode(attribute);
        return attribute;
    }

    /**
     * Gets all child key value pairs of element nodes and their corresponding text node content.
     * @param node - the upper most (root) node to begin traversing the tree structure from.
     * @return an ordered map of node name and value.
     */
    public LinkedHashMap<String, String> getAllNodeValues(Node node) {

        return getAllNodeValues(node, new LinkedHashMap<>());
    }

    /**
     * Recursively gets all child key value pairs of element nodes and their corresponding text node content.
     * @param node - the upper most (root) node to begin traversing the tree structure from.
     * @param result - an empty LinkedHashMap that will get populated through each recursive iteration.
     * @return an ordered map of node name and value.
     */
    private LinkedHashMap<String, String> getAllNodeValues(Node node, LinkedHashMap<String, String> result) {

        if(node.getNodeType() == Node.ELEMENT_NODE) {

            Node valueNode = node.getFirstChild();
            if(valueNode != null && valueNode.getNodeType() == Node.TEXT_NODE) {

                result.put(node.getNodeName(), valueNode.getTextContent().trim());
            }
            else {

                result.put(node.getNodeName(), "");
            }

            NodeList childNodes = node.getChildNodes();
            for(int i = 0; i < childNodes.getLength(); i++) {

                Node childNode = childNodes.item(i);
                if(childNode.getNodeType() == Node.ELEMENT_NODE) {

                    getAllNodeValues(childNode, result);
                }
            }
        }

        return result;
    }
}
