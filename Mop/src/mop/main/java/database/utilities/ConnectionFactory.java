package mop.main.java.database.utilities;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    Connection openConnection(ConnectionProperties connectionArgs) throws SQLException, ClassNotFoundException;
}
