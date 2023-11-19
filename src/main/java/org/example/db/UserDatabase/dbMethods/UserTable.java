package org.example.db.UserDatabase.dbMethods;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable extends CreateStatement {
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String TG_NAME = "tg_name";
    private static final String TG_ID = "tg_id";
    private static final String PLAYLISTS_IDS = "fk_playlists_ids";

    public void create(Connection connection) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " SERIAL PRIMARY KEY, " +
                TG_NAME + " VARCHAR(200), " +
                TG_ID + " VARCHAR(200)" +
                ");";
        super.create(connection, query);
    }

    public void insertRow(Connection connection, String tgName, String tgId) {
        String query = String.format("INSERT INTO %s(%s, %s)", TABLE_NAME, TG_NAME, TG_ID);
        String values = String.format(" VALUES('%s', '%s');", tgName, tgId);
        query = query.concat(values);
        super.create(connection, query);
    }
}
