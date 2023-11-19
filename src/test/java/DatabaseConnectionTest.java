import org.example.db.UserDatabase.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseConnectionTest {
    private Database db;

    @BeforeEach
    void init() {
        db = new Database();
    }

    @DisplayName("Database test connection")
    @Test
    void checkConnection() {
        assertEquals(true, db.setConnection());
//        assertEquals("My Love Mine All Mine", musicTopTracks.getTopTracks().get(0).getName());
//        assertEquals("Style (Taylor's Version)", musicTopTracks.getTopTracks().get(1).getName());
    }
}
