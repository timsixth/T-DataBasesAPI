package pl.timsixth.databasesapi.database.structure.datatype;

/**
 * Represents column data type
 */
public interface IDataType {
    /**
     * Gets data type name
     *
     * @return data type name
     */
    String getName();

    /**
     * Gets length if data type has it
     *
     * @return -1 when data type doesn't have length otherwise data type length
     */
    double getLength();

    /**
     * Sets length
     *
     * @param value new length
     */
    void setLength(double value);

    /**
     * @return true if data type has length otherwise false
     */
    boolean hasLength();

    /**
     * Gets length in correctly value for SQL database
     *
     * @return formatted length
     */
    String getFormattedLength();

    /**
     * @return true if data type is number (Boolean in MySQL is tinyint)
     */
    boolean isNumber();
}
