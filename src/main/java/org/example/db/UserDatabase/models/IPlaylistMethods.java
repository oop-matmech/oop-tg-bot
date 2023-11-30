package org.example.db.UserDatabase.models;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;

import java.util.List;

public interface IPlaylistMethods {
    public PlayListEntity findById(int id);

    public PlayListEntity findByName(String name);

    public int save(PlayListEntity playlist);

    public void update(PlayListEntity playlist);

    public void delete(PlayListEntity playlist);

    public List<PlayListEntity> findAll();
}
