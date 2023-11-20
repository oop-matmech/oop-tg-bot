package org.example.db.UserDatabase.dbMethods;

import java.sql.Connection;

public class PlayListTable extends Table {
    private static final String TABLE_NAME = "playlists";
    private static final String COLUMN_ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String USER_ID = "user_id";
    private static final String SONGS_IDS = "songs_ids";

    public void create(Connection connection) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " SERIAL PRIMARY KEY, " +
                NAME + " VARCHAR(200), " +
                DESCRIPTION + " TEXT, " +
                USER_ID + " INTEGER, " +
                "FOREIGN KEY (" + USER_ID + ") REFERENCES users(id), " +
                SONGS_IDS + " int REFERENCES songs(id)" +
                ");";
        super.createStatement(connection, query);
    }

    public void insertRow(Connection connection, String name, String description, Integer userId) {
        String query = String.format("INSERT INTO %s(%s, %s, %s)", TABLE_NAME, NAME, DESCRIPTION, USER_ID);
        String values = String.format(" VALUES('%s', '%s', '%s');", name, description, userId);
        query = query.concat(values);
        super.createStatement(connection, query);
    }
}
