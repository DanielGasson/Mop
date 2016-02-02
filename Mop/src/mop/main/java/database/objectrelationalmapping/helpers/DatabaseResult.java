package mop.main.java.database.objectrelationalmapping.helpers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.Logger;

public class DatabaseResult {

    private static final Logger log = Log.getLog(DatabaseResult.class);

    /**
     * Takes a ResultSet and parses it into a collection of rows.
     * @param <T> the data type of columns
     * @param dbResult The database result to parse
     * @return a Collection of row information consisting of name, data type, and value for each row attribute
     * @throws SQLException
     */
    public <T> List<Row> parseResultSet(ResultSet dbResult) throws SQLException {

        List<Row> rows = new ArrayList<>();

        ResultSetMetaData metaData = null;
        try {

            metaData = dbResult.getMetaData();
        }
        catch(SQLException ex) {

            log.error("Unable to extract metadata from result set.", ex);
        }

        Map<String, Class<T>> columnDetails = this.getColumnDetails(metaData);

        while(dbResult.next()) {

            Row row = new Row();
            int column = 1;

            for(Entry<String, Class<T>> entry : columnDetails.entrySet()) {

                String columnName = entry.getKey();
                Attribute<T> attribute = new Attribute<>(columnName, entry.getValue(), dbResult.getObject(columnName));

                row.addAttribute(column, attribute);
                column++;
            }
            rows.add(row);
        }

        return rows;
    }

    /**
     * Gets the name and data type of every column in a database ResultSet
     * @param metaData, the metadata of a database result set.
     * @return a Map of column name and type.  Column name is the key, data type is the value
     */
    public <T> Map<String, Class<T>> getColumnDetails(ResultSetMetaData metaData) {

        Map<String, Class<T>> columns = new HashMap<>();

        try {

            int columnCount = metaData.getColumnCount();

            for(int i = 0; i < columnCount; i++) {

                String columnName = metaData.getColumnName(i + 1);
                Class<T> columnType = Type.get(metaData.getColumnClassName(i + 1));

                columns.put(columnName, columnType);
            }
        }
        catch(SQLException ex) {

            log.error("Error extracting info from metadata.", ex);
        }

        return columns;
    }
}
