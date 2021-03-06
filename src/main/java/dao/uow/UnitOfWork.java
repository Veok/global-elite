package dao.uow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author L on 23.11.2016.
 */
public class UnitOfWork implements IUnitOfWork {

    private Connection connection;
    private List<Entity> entities = new ArrayList<Entity>();

    public UnitOfWork(Connection connection) throws SQLException {
        super();
        this.connection = connection;
        this.connection.setAutoCommit(false);
        this.connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

    }

    public void saveChanges() {
        for (Entity entity : entities) {
            entity.persist();
        }

        try {
            connection.commit();
            entities.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        entities.clear();
    }

    public void markAsNew(Entity entity) {
        entity.setState(EntityState.New);
        entities.add(entity);
    }

    public void markAsDeleted(Entity entity) {
        entity.setState(EntityState.Deleted);
        entities.add(entity);
    }

    public void markAsChanged(Entity entity) {
        entity.setState(EntityState.Changed);
        entities.add(entity);
    }

}