package pl.timsixth.databasesapi.database.async;

import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

public interface IAsyncQuery {

    int update(String query) throws ExecutionException, InterruptedException;

    ResultSet query(String query) throws ExecutionException, InterruptedException;

}
