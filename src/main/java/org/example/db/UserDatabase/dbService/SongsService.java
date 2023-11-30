package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.SongDao;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.models.ISongMethods;

public class SongsService implements ISongMethods {
    private SongDao songDao = new SongDao();

    public SongEntity findById(int id) {
        return songDao.findById(id);
    }

    public SongEntity findByName(String name) {
        return songDao.findByName(name);
    }

    public void save(SongEntity song) {
        songDao.save(song);
    }

    public void update(SongEntity song) {
        songDao.update(song);
    }

    public void delete(SongEntity song) {
        songDao.delete(song);
    }
}