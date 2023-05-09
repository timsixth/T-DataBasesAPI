package pl.timsixth.databasesapi.database.async;

import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

/**
 * Represents every sql async query
 */
public interface IAsyncQuery {
    /**
     * Executes update async query
     * @param query the query
     * @return amount of records
     * @throws ExecutionException the exception comes from Future interface
     * @throws InterruptedException the exception comes from Future interface
     */
    int update(String query) throws ExecutionException, InterruptedException;

    /**
     * Executes query async query
     * @param query the query
     * @return the resultSet
     * @throws ExecutionException the exception comes from Future interface
     * @throws InterruptedException the exception comes from Future interface
     */
    ResultSet query(String query) throws ExecutionException, InterruptedException;

}
