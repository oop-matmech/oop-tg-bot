package org.example.db.UserDatabase.models;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;

import java.util.List;
import java.util.Set;

public interface IPlaylistMethods {
    public PlayListEntity findById(int id);

    public List<PlayListEntity> findByName(String name);

    public int save(PlayListEntity playlist);

    public void update(PlayListEntity playlist);

    public void delete(PlayListEntity playlist);

    public boolean addSongToPlaylist(int playlistId, SongEntity song);

    public List<PlayListEntity> findAll();

    public Set<SongEntity> getSongsFromPlaylist(int playlistId);

    public List<PlayListEntity> getUserPlaylists(int userId);
}
