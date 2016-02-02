package mop.main.java.database.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import mop.main.java.backend.utilities.Log;

import org.apache.logging.log4j.*;

public class SqlConnectionFactory implements ConnectionFactory {

    private static final Logger log = Log.getLog(SqlConnectionFactory.class);

    public Connection openConnection(ConnectionProperties properties) throws SQLException, ClassNotFoundException {

        if(properties == null) {

            log.error("Error opening " + getClass().getName() + ".  Connection properties is null.");
            throw new IllegalArgumentException("properties");
        }

        Class.forName(properties.getDriverName());

        Properties connectionProperties = new Properties();

        connectionProperties.setProperty("user", properties.getUserName());
        connectionProperties.setProperty("password", properties.getPassword());

        //Set any additional properties
        HashMap<String, String> additionalProperties = properties.getAllProperties();
        for(Entry<String, String> entry : additionalProperties.entrySet()) {

            connectionProperties.setProperty(entry.getKey(), entry.getValue());
        }

        return DriverManager.getConnection(properties.getUrl(), connectionProperties);
    }
}
