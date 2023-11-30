package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.PlayListDao;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.models.IPlaylistMethods;

import java.util.List;

public class PlayListService implements IPlaylistMethods {
    private final PlayListDao playListDao = new PlayListDao();

    public PlayListEntity findById(int id) {
        return playListDao.findById(id);
    }

    public PlayListEntity findByName(String name) {
        return playListDao.findByName(name);
    }

    public void save(PlayListEntity playlist) {
        playListDao.save(playlist);
    }

    public void update(PlayListEntity playlist) {
        playListDao.update(playlist);
    }

    public void delete(PlayListEntity playlist) {
        playListDao.delete(playlist);
    }

    public List<PlayListEntity> findAll() {
        return playListDao.findAll();
    }

}
