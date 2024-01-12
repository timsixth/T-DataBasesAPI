package pl.timsixth.databasesapi.database.async;

import pl.timsixth.databasesapi.database.ISQLDataBase;

import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * See {@link IAsyncQuery} to more information.
 * Methods in class executes asynchronous queries to SQL database.
 */
public class AsyncQueryImpl implements IAsyncQuery {

    private final ISQLDataBase database;

    public AsyncQueryImpl(ISQLDataBase database) {
        this.database = database;
    }

    @Override
    public synchronized int update(String query) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> database.query(query).executeUpdate());
        executorService.shutdown();

        return future.get();
    }

    @Override
    public synchronized ResultSet query(String query) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<ResultSet> future = executorService.submit(() -> database.query(query).executeQuery());
        executorService.shutdown();

        return future.get();
    }

}
