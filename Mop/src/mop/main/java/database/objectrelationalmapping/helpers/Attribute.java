package mop.main.java.database.objectrelationalmapping.helpers;

public class Attribute<T> {

    private final String name;
    private final Class<T> type;
    private final Object value;

    public Attribute(String name, Class<T> type, Object value) {

        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {

        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public Class<T> getType() {

        return type;
    }

    public Object getValue() {

        return value;
    }
}
