package mop.main.java.database.objectrelationalmapping.helpers;

import java.util.HashMap;

public class Type {

    private final static HashMap<String, Object> classTypes;

    static {

        classTypes = new HashMap<>();

        classTypes.put("java.lang.String", java.lang.String.class);
        classTypes.put("java.lang.Integer", java.lang.Integer.class);
        classTypes.put("java.lang.Boolean", java.lang.Boolean.class);
        classTypes.put("java.lang.Short", java.lang.Short.class);
        classTypes.put("java.sql.Time", java.util.Date.class);
        classTypes.put("java.util.Date", java.util.Date.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> get(String dbType) {

        return (Class<T>) classTypes.get(dbType);
    }
}
