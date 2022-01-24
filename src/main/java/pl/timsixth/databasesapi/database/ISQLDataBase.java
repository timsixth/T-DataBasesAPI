package pl.timsixth.databasesapi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ISQLDataBase extends IDataBase{

    Connection getConnection();
    PreparedStatement query(String query);
}
