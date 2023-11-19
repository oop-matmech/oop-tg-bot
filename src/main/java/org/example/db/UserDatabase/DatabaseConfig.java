package org.example.db.UserDatabase;

import org.example.utils.DotenvLoader;

public class DatabaseConfig {
    public static final String DB_URL = DotenvLoader.getDotenvValue("DB_URL");
    public static final String USER = DotenvLoader.getDotenvValue("DB_USER");
    public static final String PASS = DotenvLoader.getDotenvValue("DB_PASS");
}
