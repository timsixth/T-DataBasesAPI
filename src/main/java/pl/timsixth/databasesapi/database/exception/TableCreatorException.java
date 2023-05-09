package pl.timsixth.databasesapi.database.exception;

/**
 *  This exception will be thrown when can not create table by {@link pl.timsixth.databasesapi.database.structure.ITable}
 */
public class TableCreatorException extends RuntimeException {

    public TableCreatorException(String message) {
        super(message);
    }
}
