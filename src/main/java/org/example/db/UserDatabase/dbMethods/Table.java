package org.example.db.UserDatabase.dbMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    protected void createStatement(Connection connection, String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ResultSet createStatementWithResult(Connection connection, String query) {
        Statement statement;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    protected ResultSet readAll(Connection conn, String tableName) {
        String query = String.format("select * from %s", tableName);
        ResultSet rs = createStatementWithResult(conn, query);
        return rs;
    }

    protected ResultSet getItemById(Connection connection, String tableName, String id) {
        String query = String.format("SELECT * FROM %s WHERE id = %s", tableName, id);
        ResultSet rs = createStatementWithResult(connection, query);
        return rs;
    }

    protected ResultSet getItemByName(Connection connection, String tableName, String name) {
        String query = String.format("SELECT * FROM %s WHERE name = %s", tableName, name);
        ResultSet rs = createStatementWithResult(connection, query);
        return rs;
    }

    protected void dropTable(Connection connection, String tableName) {
        String query = String.format("DROP TABLE %s", tableName);
        createStatement(connection, query);
    }

    protected void deleteByName(Connection connection, String tableName, String name) {
        String query = String.format("delete from %s where name='%s'", tableName, name);
        createStatement(connection, query);
    }

    protected void deleteById(Connection connection, String tableName, Integer id) {
        String query = String.format("delete from %s where id='%s'", tableName, id);
        createStatement(connection, query);
    }
}
