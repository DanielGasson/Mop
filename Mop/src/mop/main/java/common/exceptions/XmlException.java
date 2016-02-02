package mop.main.java.common.exceptions;

public class XmlException extends Exception {

    public XmlException(String cause) {

        super(cause);
    }

    public XmlException(Exception inner) {

        super(inner);
    }

    public XmlException(String cause, Exception inner) {

        super(cause, inner);
    }
}

