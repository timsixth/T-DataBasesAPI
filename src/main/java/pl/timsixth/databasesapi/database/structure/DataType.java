package pl.timsixth.databasesapi.database.structure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents data types in SQL databases
 * The enum is deprecated because in next version this enum will be replaced in data types classes
 *
 * @deprecated
 */
@Deprecated
@RequiredArgsConstructor
@Getter
public enum DataType {

    VARCHAR("varchar"),
    DATE("date"),
    BOOLEAN("boolean"),
    INT("int"),
    INTEGER("integer");

    private final String textNameDateType;
}
