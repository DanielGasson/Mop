package mop.main.java.database.dataaccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import mop.main.java.backend.caching.DatabaseCache;
import mop.main.java.backend.utilities.Log;
import mop.main.java.backend.utilities.MethodGenerator;
import mop.main.java.common.exceptions.EntityMetadataException;
import mop.main.java.database.model.queryable.Queryable;
import mop.main.java.database.model.writable.Writable;
import mop.main.java.database.objectrelationalmapping.AutoMapper;
import mop.main.java.database.objectrelationalmapping.helpers.DatabaseResult;
import mop.main.java.database.objectrelationalmapping.helpers.Row;
import mop.main.java.database.utilities.ConnectionFactory;
import mop.main.java.database.utilities.ConnectionProperties;
import mop.main.java.database.objectrelationalmapping.helpers.SqlGenerator;

import org.apache.logging.log4j.Logger;

public class MopRepository implements Repository {

    private static final Logger log = Log.getLog(MopRepository.class);

    private final AutoMapper mapper;
    private final SqlGenerator sqlGenerator;
    private final DatabaseResult dbResult;
    private final ConnectionFactory connectionFactory;

    public MopRepository(ConnectionFactory connectionFactory) {

        this.connectionFactory = connectionFactory;

        mapper = new AutoMapper();
        sqlGenerator = new SqlGenerator();
        dbResult = new DatabaseResult();
    }

    public <T extends Queryable> T get(int id, Class<T> entityType, ConnectionProperties connectionProperties) throws SQLException {

        Connection connection = this.openConnection(connectionProperties);

        String query = sqlGenerator.generateGetScript(entityType.getSimpleName(), id, DatabaseCache.getInstance());

        T result = null;
        try {

            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<Row> rows = dbResult.parseResultSet(resultSet);
            if(rows.size() < 1) {

                return null;
            }
            result = mapper.mapSingleDatabaseRow(entityType, rows.get(0));
        }
        catch(SQLException ex) {

            log.error("Error getting entity.", ex);
        }
        finally {

            if (connection != null) {

                connection.close();
            }
        }

        return result;
    }

    public <T extends Writable> boolean save(T entity, ConnectionProperties connectionProperties) throws SQLException {

        Connection connection = this.openConnection(connectionProperties);
        Statement statement = connection.createStatement();

        try {

            String[] scripts = sqlGenerator.generateSaveScripts(entity, DatabaseCache.getInstance());
            for(String script : scripts) {

                statement.addBatch(script);
            }

            statement.executeBatch();

            return true;
        }
        catch(EntityMetadataException ex) {

            log.error("Error saving entity.", ex);
            return false;
        }
        finally {

            connection.close();
        }
    }

    public <T extends Writable> boolean update(T entity, ConnectionProperties connectionProperties) throws SQLException {

        throw new UnsupportedOperationException();
    }

    public <T> boolean delete(T entity, ConnectionProperties connectionProperties) throws SQLException {

        Connection connection = this.openConnection(connectionProperties);
        int id;

        try {

            Method getId = MethodGenerator.generatePrimaryKeyAccessor(entity);
            id = (int) getId.invoke(entity);
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {

            log.error("Unable to get entity id to perform delete operation.", ex);
            return false;
        }

        String script = sqlGenerator.generateDeleteScript(entity.getClass().getSimpleName(), id, DatabaseCache.getInstance());

        return connection.createStatement().execute(script);
    }

    public <T extends Queryable> List<T> executeSql() {

        // todo
        throw new UnsupportedOperationException();
    }

    private Connection openConnection(ConnectionProperties connectionProperties) {

        Connection connection = null;
        try {

            connection = connectionFactory.openConnection(connectionProperties);
        }
        catch(SQLException | ClassNotFoundException ex) {

            log.error("Unable to open connection to database.", ex);
        }

        return connection;
    }
}
