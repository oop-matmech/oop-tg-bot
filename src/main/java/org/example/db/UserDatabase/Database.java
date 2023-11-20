package org.example.db.UserDatabase;

import org.example.db.UserDatabase.dbMethods.PlayListTable;
import org.example.db.UserDatabase.dbMethods.SongsTable;
import org.example.db.UserDatabase.dbMethods.UserTable;
import org.example.db.UserDatabase.dbMethods.SetDbConnection;

import java.sql.Connection;

public class Database implements IDbMethods {

    private Connection connection = null;
    private SongsTable songsTable = new SongsTable();
    private UserTable userTable = new UserTable();
    private PlayListTable playListTable = new PlayListTable();

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean setConnection() {
        SetDbConnection setConnection = new SetDbConnection();
        this.connection = setConnection.make(connection);
        if (this.connection != null) {
            System.out.println("You successfully connected to database now");
            return true;
        } else {
            System.out.println("Failed to make connection to database");
            return false;
        }
    }

    @Override
    public void initializeDb() {
        System.out.println(connection.toString());
        userTable.create(connection);
        songsTable.create(connection);
        playListTable.create(connection);
        System.out.println("INITIALIZED TABLES");
    }

    public UserTable getUserTable() {
        return this.userTable;
    }


}
