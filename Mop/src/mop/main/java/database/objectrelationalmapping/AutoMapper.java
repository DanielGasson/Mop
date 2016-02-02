package mop.main.java.database.objectrelationalmapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import mop.main.java.backend.utilities.Log;
import mop.main.java.database.objectrelationalmapping.helpers.Attribute;
import mop.main.java.database.objectrelationalmapping.helpers.Row;

import org.apache.logging.log4j.Logger;

public class AutoMapper {

    private static final Logger log = Log.getLog(AutoMapper.class);

    /**
     * Maps a collection of database rows to an ArrayList of object T
     * @param type, the class type to map to
     * @param rows, a collection of Row objects to map
     * @param <T> the class type to map to
     * @return a List of T, the mapped type
     */
    public <T> List<T> mapMultipleDatabaseRows(Class<T> type, Collection<Row> rows) {

        List<T> result = new ArrayList<>();

        rows.stream().forEach((row) -> {

            T mappedResult = this.mapSingleDatabaseRow(type, row);
            result.add(mappedResult);
        });

        return result;
    }

    /**
     * Maps a database row to an object T
     * @param type, the class type to map to
     * @param row, a Row object that comprises database attributes
     * @param <T> the class type to map to
     * @return T, the mapped type
     */
    public <T> T mapSingleDatabaseRow(Class<T> type, Row row) {

        T instance = null;

        try {

            instance = type.newInstance();

            for(Map.Entry<Integer, Attribute> property : row.getRowDetails().entrySet()) {

                try {

                    if(property.getValue().getValue() != null) {

                        Method method = type.getMethod("set" + property.getValue().getName(), property.getValue().getType());
                        try {

                            method.invoke(instance, property.getValue().getValue());
                        }
                        catch (IllegalArgumentException ex) {

                            log.error(
                                "Method '" + method.getName() +
                                "' expects " + property.getValue().getType().getName() +
                                " but was called with " + property.getValue().getValue().getClass().getName(), ex);
                        }
                        catch (InvocationTargetException ex) {

                            log.error("Method " + method.getName() + "has thrown an underlying exception.", ex);
                        }
                    }
                }
                catch (NoSuchMethodException ex) {

                    log.error("Method 'set" + property.getValue().getName() + "' with parameter " + property.getValue().getType().getName() + "not found" , ex);
                }
            }
        }
        catch (InstantiationException ex) {

            log.error("Unable to create instance of " + type.getName() + ".", ex);
        }
        catch (IllegalAccessException ex) {

            log.error("Unable to create instance of " + type.getName() + " as class and/or constructor not accessible.", ex);
        }

        return instance;
    }
}
