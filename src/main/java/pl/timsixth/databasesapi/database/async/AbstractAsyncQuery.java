package pl.timsixth.databasesapi.database.async;

import pl.timsixth.databasesapi.database.ISQLDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractAsyncQuery<T extends ISQLDataBase> implements IAsyncQuery{

    private final T database;

    public AbstractAsyncQuery(T database) {
        this.database = database;
    }

    @Override
    public int update(String query) {
        AtomicInteger amountOfRecords = new AtomicInteger();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            PreparedStatement preparedStatement = database.query(query);
            try {
                amountOfRecords.set(preparedStatement.executeUpdate());
            } catch (SQLException e) {
                System.out.println("Executing query generated error with the content: " + e.getMessage());
            }
        });
        executorService.shutdown();
        return amountOfRecords.get();
    }

    @Override
    public ResultSet query(String query) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicReference<ResultSet> resultSet = new AtomicReference<>();
        executorService.submit(() -> {
            PreparedStatement preparedStatement = database.query(query);
            try {
                resultSet.set(preparedStatement.executeQuery());
            } catch (SQLException e) {
                System.out.println("Executing query generated error with the content: " + e.getMessage());
            }
        });
        executorService.shutdown();
        return resultSet.get();
    }
}
