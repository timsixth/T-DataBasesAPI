package pl.timsixth.databasesapi.database;

import pl.timsixth.databasesapi.database.async.IAsyncQuery;
import pl.timsixth.databasesapi.database.structure.ITable;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Represents every SQL database
 */
public interface ISQLDataBase extends IDataBase {
    /**
     * @return information about connection
     */
    Connection getConnection();

    /**
     * Executes sync query to database
     *
     * @param query given query
     * @return result of query
     */
    PreparedStatement query(String query);

    /**
     * @return interface to create tables
     */
    ITable getTableCreator();

    /**
     * @return interface to executing async queries
     */
    IAsyncQuery getAsyncQuery();
}
