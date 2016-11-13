package dao;

import java.sql.Connection;

/**
 * Created by L on 13.11.2016.
 */
public class ScoreBoardRepository extends RepositoryBase {

    public ScoreBoardRepository(Connection connection) {
        super(connection);
    }


    @Override
    protected String createTableSql() {
        return "" + "CREATE TABLE SCOREBOARD("
                + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
                + "killsInMatch int,"
                + "deathsInMatch int,"
                + "PLAYER_ID int,"
                + "FOREIGN KEY (PLAYER_ID) REFERENCES PLAYER(id)" + ")";
    }

    @Override
    protected String tableName() {
        return "SCOREBOARD";
    }
}