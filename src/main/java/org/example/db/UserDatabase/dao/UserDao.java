package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.models.IUserMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao implements IUserMethods {
    public UserEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    public UserEntity findByName(String name) {
        try {
            UserEntity user = findAll().stream().filter(it -> it.getName() == name).toList().get(0);
            return user;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public void save(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public void addPlaylist(int userId, PlayListEntity playList) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        UserEntity user = findById(userId);
        user.addPlaylist(playList);
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void removePlaylist(int userId, int playListId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        UserEntity user = findById(userId);
        user.removePlaylist(playListId);
        session.update(user);
        tx1.commit();
        session.close();
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users = HibernateUtils.getSessionFactory().openSession().createQuery("From users", UserEntity.class).list();
        return users;
    }
}
