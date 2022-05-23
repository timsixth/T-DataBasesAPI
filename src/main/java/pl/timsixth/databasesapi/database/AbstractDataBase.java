package pl.timsixth.databasesapi.database;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    @Override
    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

}
