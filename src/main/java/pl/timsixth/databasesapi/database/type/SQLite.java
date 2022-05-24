package pl.timsixth.databasesapi.database.type;

import lombok.NoArgsConstructor;
import pl.timsixth.databasesapi.database.AbstractDataBase;
import pl.timsixth.databasesapi.database.ISQLite;
import pl.timsixth.databasesapi.database.async.IAsyncQuery;
import pl.timsixth.databasesapi.database.async.sqlite.AsyncQuerySqlite;
import pl.timsixth.databasesapi.database.structure.ITable;
import pl.timsixth.databasesapi.database.structure.sqlite.SqliteTable;
import pl.timsixth.databasesapi.spigot.DatabasesApiPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@NoArgsConstructor
public class SQLite extends AbstractDataBase implements ISQLite {

    private Connection connection;

    private File databaseFile;

    private DatabasesApiPlugin databasesApiPlugin;

    public SQLite(DatabasesApiPlugin databasesApiPlugin){
        this.databasesApiPlugin = databasesApiPlugin;
    }
    @Override
    public void openConnection(File file) throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        String url = "jdbc:sqlite:" + file;
        databaseFile = file;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(url);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IAsyncQuery getAsyncQuery() {
        return new AsyncQuerySqlite(this);
    }

    @Override
    public PreparedStatement query(String query) {
        PreparedStatement preparedStatement = null;
        try {

            if (connection == null) {
                openConnection(databaseFile);
            } else if (connection.isClosed()) {
                openConnection(databaseFile);
            }

            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public ITable getTableCreator() {
        return new SqliteTable(this);
    }

    @Override
    public File createDataBase(String name) throws IOException {
        if (!databasesApiPlugin.getDataFolder().mkdir()) {
            databasesApiPlugin.getDataFolder().mkdirs();
        }
        File file = new File(databasesApiPlugin.getDataFolder(), name);

        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
