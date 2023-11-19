package org.example.db.UserDatabase;

import org.example.db.UserDatabase.dbMethods.PlayListTable;
import org.example.db.UserDatabase.dbMethods.SongsTable;
import org.example.db.UserDatabase.dbMethods.UserTable;
import org.example.db.UserDatabase.dbMethods.SetDbConnection;

import java.sql.Connection;

public class Database implements IDbMethods {

    private Connection connection = null;
    private final SongsTable songsTable = new SongsTable();
    private final UserTable userTable = new UserTable();
    private final PlayListTable playListTable = new PlayListTable();

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean setConnection() {
        SetDbConnection setConnection = new SetDbConnection();
        connection = setConnection.make(connection);
        if (connection != null) {
            System.out.println("You successfully connected to database now");
            return true;
        } else {
            System.out.println("Failed to make connection to database");
            return false;
        }
    }

    @Override
    public void initializeDb() {
        userTable.create(connection);
        songsTable.create(connection);
        playListTable.create(connection);
    }
}
