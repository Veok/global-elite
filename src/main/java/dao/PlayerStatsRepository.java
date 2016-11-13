package dao;

import java.sql.Connection;

/**
 * Created by L on 13.11.2016.
 */
public class PlayerStatsRepository extends RepositoryBase {

    public PlayerStatsRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String createTableSql() {
        return "" + "CREATE TABLE PLAYERSTATS("
                + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
                + "kills int,"
                + "deaths int,"
                + "ratio double" + ")";
    }

    @Override
    protected String tableName() {
        return "PLAYERSTATS";
    }
}