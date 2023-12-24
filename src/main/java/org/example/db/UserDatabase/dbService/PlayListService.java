package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.PlayListDao;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.models.IPlaylistMethods;

import java.util.List;
import java.util.Set;

public class PlayListService implements IPlaylistMethods {
    private final PlayListDao playListDao = new PlayListDao();

    public PlayListEntity findById(int id) {
        return playListDao.findById(id);
    }

    public List<PlayListEntity> findByName(String name) {
        return playListDao.findByName(name);
    }

    public int save(PlayListEntity playlist) {
        return playListDao.save(playlist);
    }

    public void update(PlayListEntity playlist) {
        playListDao.update(playlist);
    }

    public void delete(PlayListEntity playlist) {
        playListDao.delete(playlist);
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, SongEntity song) {
        return playListDao.addSongToPlaylist(playlistId, song);
    }

    public List<PlayListEntity> findAll() {
        return playListDao.findAll();
    }

    @Override
    public Set<SongEntity> getSongsFromPlaylist(int playlistId) {
        return playListDao.getSongsFromPlaylist(playlistId);
    }

    @Override
    public List<PlayListEntity> getUserPlaylists(int userId) {
        return playListDao.getUserPlaylists(userId);
    }

    public PlayListEntity findByNameFromUser(UserEntity userEntity, String userPlName) {
        List<PlayListEntity> currentUserPlaylists = userEntity.getPlaylists();
        for (PlayListEntity elem : currentUserPlaylists) {
            if (elem.getName().equals(userPlName)) {
                return elem;
            }
        }
        return new PlayListEntity();
    }

    public boolean userAlreadyHasPlayList(UserEntity userEntity, String userPlName) {
        List<PlayListEntity> currentUserPlaylists = userEntity.getPlaylists();
        for (PlayListEntity elem : currentUserPlaylists) {
            if (elem.getName().equals(userPlName)) {
                return true;
            }
        }
        return false;
    }
}
