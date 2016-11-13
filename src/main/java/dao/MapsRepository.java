package dao;

import java.sql.Connection;

/**
 * Created by L on 13.11.2016.
 */
public class MapsRepository extends RepositoryBase {

    public MapsRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String createTableSql() {
        return "" + "CREATE TABLE MAPS("
                + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
                + "name varchar(25)," + ")";
    }

    @Override
    protected String tableName() {
        return "MAPS";
    }
}