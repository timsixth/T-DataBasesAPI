package pl.timsixth.databasesapi.database.async;

import java.sql.ResultSet;

public interface IAsyncQuery {

    int update(String query);

    ResultSet query(String query);

}
