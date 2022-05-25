package pl.timsixth.databasesapi.database.async;

import pl.timsixth.databasesapi.database.ISQLDataBase;

import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class AbstractAsyncQuery<T extends ISQLDataBase> implements IAsyncQuery {

    private final T database;

    public AbstractAsyncQuery(T database) {
        this.database = database;
    }

    @Override
    public synchronized int update(String query) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> database.query(query).executeUpdate());
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
