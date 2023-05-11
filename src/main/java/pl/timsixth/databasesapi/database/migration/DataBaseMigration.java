package pl.timsixth.databasesapi.database.migration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents every migration from database
 */
@AllArgsConstructor
@Getter
@Setter
public class DataBaseMigration {

    private final String tableName;
    private int currentVersion;

}
