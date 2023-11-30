package org.example.db.UserDatabase.models;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;

import java.util.List;

public interface IUserMethods {
    public UserEntity findById(int id);

    public UserEntity findByName(String name);

    public void save(UserEntity user);

    public void update(UserEntity user);

    public void delete(UserEntity user);

    public void addPlaylist(int userId, PlayListEntity playList);

    public void removePlaylist(int userId, int playListId);

    public List<UserEntity> findAll();
}
