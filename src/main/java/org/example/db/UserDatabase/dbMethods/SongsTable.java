package org.example.db.UserDatabase.dbMethods;

import java.sql.Connection;

public class SongsTable extends Table {
    private static final String TABLE_NAME = "songs";
    private static final String COLUMN_ID = "id";
    private static final String NAME = "name";
    private static final String DURATION = "duration";
    private static final String URL = "url";
    private static final String ARTIST_NAME = "artist_name";

    public void create(Connection connection) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " SERIAL PRIMARY KEY, " +
                NAME + " VARCHAR(200), " +
                DURATION + " INTEGER, " +
                URL + " TEXT, " +
                ARTIST_NAME + " VARCHAR(200)" +
                ");";
        super.createStatement(connection, query);
    }

    public void insertRow(Connection connection, String name, Integer duration, String url, String artistName) {
        String query = String.format("INSERT INTO %s(%s, %s, %s, %s)", TABLE_NAME, NAME, DURATION, URL, ARTIST_NAME);
        String values = String.format(" VALUES('%s', '%s', '%s', '%s');", name, duration, url, artistName);
        query = query.concat(values);
        super.createStatement(connection, query);
    }
}