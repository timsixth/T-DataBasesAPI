package pl.timsixth.databasesapi.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is using template method design pattern.
 * See {@link IDataBase} to more information
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractDataBase implements IDataBase {

    private String hostname, username, password, dataBase;
    private int port;

}
