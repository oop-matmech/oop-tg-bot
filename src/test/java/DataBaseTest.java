import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.StatsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataBaseTest {
    private PlayListService playListService;
    private SongsService songsService;
    private UserService userService;
    private StatsService statsService;

    @BeforeEach
    void init() {
        playListService = new PlayListService();
        songsService = new SongsService();
        userService = new UserService();
        statsService = new StatsService();
    }

    @DisplayName("playlist test")
    @Test
    void testPlaylistMethods() {
        List<PlayListEntity> lst = new ArrayList<>();
        assertEquals(lst, playListService.findByName(""));
        assertEquals(null, playListService.findById(-1));
    }

    @DisplayName("usr test")
    @Test
    void testUsrMethods() {
        assertEquals(null, userService.findByName(""));
        assertEquals(null, userService.findById(-1));
    }

    @DisplayName("stats test")
    @Test
    void testStatsMethods() {
        assertEquals(null, statsService.findById(100000000));
    }

    @DisplayName("songs test")
    @Test
    void testSongsMethods() {
        assertEquals(null, songsService.findByName(""));
        assertEquals(null, songsService.findById(-1));
        assertEquals(null, songsService.findByUrl(""));

    }
}
