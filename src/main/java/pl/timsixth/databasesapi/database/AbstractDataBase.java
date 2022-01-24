package pl.timsixth.databasesapi.database;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDataBase implements IDataBase {

    private String hostname, username, password, database;
    private int port;

    @Override
    public String getDataBase() {
        return database;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
