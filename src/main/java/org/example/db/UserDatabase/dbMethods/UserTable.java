package org.example.db.UserDatabase.dbMethods;

import org.example.db.UserDatabase.dbEntities.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class UserTable extends Table {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String TG_NAME = "name";
    public static final String TG_ID = "tg_id";
    public static final String PLAYLISTS_IDS = "fk_playlists_ids";

    public void create(Connection connection) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " SERIAL PRIMARY KEY, " +
                TG_NAME + " VARCHAR(200), " +
                TG_ID + " VARCHAR(200)" +
                ");";
        super.createStatement(connection, query);
    }

    public void insertRow(Connection connection, String tgName, String tgId) {
        String query = String.format("INSERT INTO %s(%s, %s)", TABLE_NAME, TG_NAME, TG_ID);
        String values = String.format(" VALUES('%s', '%s');", tgName, tgId);
        query = query.concat(values);
        super.createStatement(connection, query);
    }

    public ResultSet getAll(Connection connection) {
        ResultSet rs = super.readAll(connection, TABLE_NAME);
        return rs;
    }

    public ResultSet getItemByName(Connection connection, String name) {
        ResultSet rs = super.getItemByName(connection, TABLE_NAME, name);
        return rs;
    }

    public ResultSet getItemById(Connection connection, String id) {
        ResultSet rs = super.getItemById(connection, TABLE_NAME, id);
        return rs;
    }

    public void updateUser(Connection connection, UserEntity user) {
        String query = String.format(
                "update %s set " +
                        TG_NAME + " ='%s' " +
                        PLAYLISTS_IDS + " ='%s' " +
                        "where " + TG_ID + "='%s'",
                TABLE_NAME, user.getName(), user.getFk_playlists_ids(), user.getId());
        super.createStatement(connection, query);
    }
}
