import org.example.db.UserDatabase.Database;
import org.example.db.UserDatabase.dbMethods.UserTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseConnectionTest {
    private Database db;
    private UserTable userTable;

    @BeforeEach
    void init() {
        userTable = new UserTable();
        db = new Database();
    }

    @DisplayName("Database test connection")
    @Test
    void checkConnection() {
        assertEquals(true, db.setConnection());
    }

    @DisplayName("Database insert row")
    @Test
    void checkInsert() {
        Connection connection = null;
        assertEquals(null, userTable.getItemByName(connection, ""));
    }
}
