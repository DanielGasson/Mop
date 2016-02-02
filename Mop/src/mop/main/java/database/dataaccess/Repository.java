package mop.main.java.database.dataaccess;

import java.sql.SQLException;

import mop.main.java.database.model.queryable.Queryable;
import mop.main.java.database.model.writable.Writable;
import mop.main.java.database.utilities.ConnectionProperties;

public interface Repository {

    /**
     * Get a single entity by it's id.
     * @param id, the id of the entity.
     * @param entityType, the entity type to be retrieved.
     * @param connectionProperties, properties used to establish a connection with a database.
     * @return the entity T.
     * @throws SQLException
     */
    <T extends Queryable> T get(int id, Class<T> entityType, ConnectionProperties connectionProperties) throws SQLException;

    /**
     * Save an entity.
     * @param entity, the entity to save.
     * @param connectionProperties, properties used to establish a connection with a database.
     * @return true if the entity is successfully saved.
     * @throws SQLException
     */
    <T extends Writable> boolean save(T entity, ConnectionProperties connectionProperties) throws SQLException;

    /**
     * Update an existing entity.
     * @param entity, the entity to update.
     * @param connectionProperties, properties used to establish a connection with a database.
     * @return true if the entity is successfully updated.
     * @throws SQLException
     */
    <T extends Writable> boolean update(T entity, ConnectionProperties connectionProperties) throws SQLException;

    /**
     * Delete a single entity.
     * @param entity, the entity to delete.
     * @param connectionProperties, properties used to establish a connection with a database.
     * @return true if the entity is successfully deleted.
     * @throws SQLException
     */
    <T> boolean delete(T entity, ConnectionProperties connectionProperties) throws SQLException;
}
