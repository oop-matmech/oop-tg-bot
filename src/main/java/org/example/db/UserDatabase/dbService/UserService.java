package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.UserDao;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.models.IUserMethods;

import java.util.List;

public class UserService implements IUserMethods {
    private UserDao userDao = new UserDao();

    public UserEntity findById(int id) {
        return userDao.findById(id);
    }

    public UserEntity findByName(String name) {
        return userDao.findByName(name);
    }

    public void save(UserEntity user) {
        userDao.save(user);
    }

    public void update(UserEntity user) {
        userDao.update(user);
    }

    public void delete(UserEntity user) {
        userDao.delete(user);
    }

    public void addPlaylist(int userId, PlayListEntity playList) {
        userDao.addPlaylist(userId, playList);
    }

    public void removePlaylist(int userId, int playListId) {
        userDao.removePlaylist(userId, playListId);
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }
}