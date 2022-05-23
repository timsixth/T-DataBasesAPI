package pl.timsixth.databasesapi.database.structure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DataType {

    VARCHAR("varchar"),
    DATE("date"),
    BOOLEAN("boolean"),
    INT("int");

    private final String stringDateType;
}
