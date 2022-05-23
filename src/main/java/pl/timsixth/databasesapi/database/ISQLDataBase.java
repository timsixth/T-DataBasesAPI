package pl.timsixth.databasesapi.database;

import pl.timsixth.databasesapi.database.structure.ITable;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ISQLDataBase extends IDataBase{

    Connection getConnection();
    PreparedStatement query(String query);
    ITable getTableCreator();
}
