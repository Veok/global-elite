package dao;

import dao.mappers.IMapResultSetIntoEntity;
import dao.repositories.IEnumDictionariesRepository;
import dao.uow.IUnitOfWork;
import domain.model.EnumDictionary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author L on 16.11.2016.
 */
public class EnumDictionariesRepository extends RepositoryBase<EnumDictionary> implements IEnumDictionariesRepository {

    protected PreparedStatement selectWithDictionaryName;
    protected PreparedStatement getEnumValuesByString;
    protected PreparedStatement getEnumValuesByInt;

    protected String selectWithDictionaryName() {
        return "SELECT * FROM ENUM_DICTIONARY WHERE enumerationName=?";
    }

    protected String getEnumValuesByString() {
        return "SELECT value FROM ENUM_DICTIONARY WHERE enumerationName=? AND stringKey=?";
    }

    protected String getEnumValuesByInt() {
        return "SELECT value FROM ENUM_DICTIONARY WHERE enumerationName=? AND intKey=?";
    }

    public EnumDictionariesRepository(Connection connection, IMapResultSetIntoEntity<EnumDictionary> mapper, IUnitOfWork uow) {
        super(connection, mapper, uow);
        try {
            selectWithDictionaryName = connection.prepareStatement(selectWithDictionaryName());
            getEnumValuesByString = connection.prepareStatement(getEnumValuesByString());
            getEnumValuesByInt = connection.prepareStatement(getEnumValuesByInt());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<EnumDictionary> getEnumValues(String enumName, String stringKey) {
        List<EnumDictionary> result = new ArrayList<EnumDictionary>();
        try {
            getEnumValuesByString.setString(1, enumName);
            getEnumValuesByString.setString(2, stringKey);
            ResultSet rs = getEnumValuesByString.executeQuery();
            while (rs.next()) {
                result.add(mapper.map(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<EnumDictionary> withDictionaryName(String enumName) {
        return searchByString(enumName, getEnumValuesByString);
    }

    public List<EnumDictionary> getEnumValues(String enumName, int intKey) {
        List<EnumDictionary> result = new ArrayList<EnumDictionary>();
        try {
            getEnumValuesByInt.setString(1, enumName);
            getEnumValuesByInt.setInt(2, intKey);
            ResultSet rs = getEnumValuesByString.executeQuery();
            while (rs.next()) {
                result.add(mapper.map(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @Override
    protected String createTableSql() {
        return "CREATE TABLE ENUM_DICTIONARY("
                + "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
                + "intKey bigint,"
                + "stringKey varchar(50),"
                + "value varchar(50),"
                + "enumerationName varchar(50)"
                + ")";
    }

    @Override
    protected String tableName() {
        return "ENUM_DICTIONARY";
    }

    protected String insertSql() {
        return "INSERT INTO ENUM_DICTIONARY(intKey, stringKey, value, enumerationName) VALUES (?,?,?,?)";
    }

    protected String updateSql() {
        return "UPDATE ENUM_DICTIONARY SET (intKey, stringKey, value, enumerationName)=(?,?,?,?) WHERE id=?";
    }

    @Override
    protected void setUpdate(EnumDictionary entity) throws SQLException {
        update.setInt(1, entity.getIntKey());
        update.setString(2, entity.getStringKey());
        update.setString(3, entity.getValue());
        update.setString(4, entity.getEnumerationName());
    }

    @Override
    protected void setInsert(EnumDictionary entity) throws SQLException {
        insert.setInt(1, entity.getIntKey());
        insert.setString(2, entity.getStringKey());
        insert.setString(3, entity.getValue());
        insert.setString(4, entity.getEnumerationName());
    }

}