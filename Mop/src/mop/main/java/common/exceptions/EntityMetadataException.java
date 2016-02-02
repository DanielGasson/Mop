package mop.main.java.common.exceptions;

public class EntityMetadataException extends Exception {

    public EntityMetadataException(String cause) {

        super(cause);
    }

    public EntityMetadataException(Exception inner) {

        super(inner);
    }

    public EntityMetadataException(String cause, Exception inner) {

        super(cause, inner);
    }
}
