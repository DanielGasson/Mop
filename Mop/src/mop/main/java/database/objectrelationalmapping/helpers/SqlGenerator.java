package mop.main.java.database.objectrelationalmapping.helpers;

import java.util.ArrayList;

import mop.main.java.backend.caching.Cache;
import mop.main.java.backend.utilities.Log;
import mop.main.java.common.Constants;
import mop.main.java.common.exceptions.EntityMetadataException;
import mop.main.java.database.model.writable.Writable;

import org.apache.logging.log4j.Logger;

public class SqlGenerator {

    private static final Logger log = Log.getLog(SqlGenerator.class);

    /**
     * Generates the a sql string to delete an entity.
     * @param entityType, the name of the entity to be retrieved
     * @param id, the id of the entity to be retrieved
     * @return the get script as a string
     */
    public String generateGetScript(String entityType, int id, Cache cache) {

        if(entityType == null || entityType.equals("")) {

            log.error("Cannot generate get script with null or empty entity name.");
            throw new IllegalArgumentException("Cannot generate get script with null or empty entity name.");
        }

        return String.format("SELECT * FROM %1$s.%2$s WHERE %2$sId = %3$d LIMIT 1",
            cache.get(Constants.AppConfigSections.defaultSchema),
            entityType,
            id
        );
    }

    /**
     * Generates scripts to save an entity and any related entities.
     * @param entity, the entity to save
     * @return an array of string scripts
     * @throws EntityMetadataException
     */
    public <T extends Writable> String[] generateSaveScripts(T entity, Cache cache) throws EntityMetadataException {

        EntityMetadata metadata = entity.getMetadata(null);

        ArrayList<Entity> entities = metadata.getEntities();
        String[] scripts = new String[entities.size()];

        for(int i = 0; i < entities.size(); i++) {

            Entity temp = entities.get(i);

            StringBuilder values = new StringBuilder();
            StringBuilder columns = new StringBuilder();
            StringBuilder script = this.constructHeader((String) cache.get(Constants.AppConfigSections.defaultSchema), temp.getTableName().toString());

            script.append("(");
            values.append(" VALUES (");

            ArrayList<Attribute> attributes = temp.getAttributes();

            for(int a = 0; a < attributes.size(); a ++) {

                Attribute attribute = attributes.get(a);
                columns.append(attribute.getName());
                String value = this.formatValue(attribute);
                values.append(value);

                if((a + 1) < attributes.size()) {

                    columns.append(", ");
                    values.append(", ");
                }
            }

            columns.append(")");
            values.append(");");
            script.append(columns);
            script.append(values);

            scripts[i] = script.toString();
        }

        return scripts;
    }

    /**
     * Generates the a sql string to delete an entity.
     * @param entityType, the name of the entity to be deleted
     * @param id, the id of the entity to be deleted
     * @return the delete script as a string
     */
    public String generateDeleteScript(String entityType, int id, Cache cache) {

        if(entityType == null || entityType.equals("")) {

            log.error("Cannot generate delete script with null or empty entity name.");
            throw new IllegalArgumentException("Cannot generate delete script with null or empty entity name.");
        }

        return String.format("DELETE FROM %1$s.%2$s WHERE %2$sId = %3$d",
            cache.get(Constants.AppConfigSections.defaultSchema),
            entityType,
            id
        );
    }

    /**
     * Prepares a field value for use in a sql statement.
     * @param attribute, contains the field type, and value
     * @return a string representation of
     */
    private String formatValue(Attribute attribute) {

        // if the attribute is a String then surround with quotes
        if(attribute.getType() == String.class) {

            return "'" + attribute.getValue() + "'";
        }

        // if the field is a boolean then convert to either 0 or 1
        if(attribute.getType() == boolean.class) {

            return (boolean) attribute.getValue() ? "1" : "0";
        }

        return attribute.getValue() + "";
    }

    private StringBuilder constructHeader(String schema, String table) {

        StringBuilder builder = new StringBuilder();

        builder.append("INSERT INTO ");
        builder.append(schema);
        builder.append(".");
        builder.append(table);
        builder.append(" ");

        return builder;
    }
}
