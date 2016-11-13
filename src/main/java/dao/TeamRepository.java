package dao;

import java.sql.Connection;

/**
 * Created by L on 13.11.2016.
 */
public class TeamRepository extends RepositoryBase {

    public TeamRepository(Connection connection) {
        super(connection);
    }


    @Override
    protected String createTableSql() {
        return "" + "CREATE TABLE TEAM("
                + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
                + "name varchar(25),"
                + "country varchar(25),"
                + "TEAMSTATS_ID int,"
                + "FOREIGN KEY (TEAMSTATS_ID) REFERENCES TEAMSTATS(id)" + ")";
    }

    @Override
    protected String tableName() {
        return "TEAM";
    }
}