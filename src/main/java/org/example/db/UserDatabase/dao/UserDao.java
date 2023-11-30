package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.models.IUserMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements IUserMethods {

    private final PlayListService playListService = new PlayListService();

    public UserEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    public UserEntity findByName(String name) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            Query<UserEntity> query = session.createQuery("From UserEntity where name='" + name + "'", UserEntity.class);
            UserEntity user = query.getSingleResultOrNull();
            tx1.commit();
            session.close();
            return user;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public void save(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }

    public void update(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(user);
        tx1.commit();
        session.close();
    }

    public void delete(UserEntity user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(user);
        tx1.commit();
        session.close();
    }

    public void addPlaylist(int userId, PlayListEntity playList) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        UserEntity user = findById(userId);
        int plId = playListService.save(playList);
        playList.setId(plId);
        user.addPlaylist(playList);
        session.merge(user);
        tx1.commit();
        session.close();
    }

    public void removePlaylist(int userId, int playListId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        UserEntity user = findById(userId);
        user.removePlaylist(playListId);
        session.merge(user);
        tx1.commit();
        session.close();
    }

    public List<UserEntity> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<UserEntity> query = session.createQuery("From UserEntity", UserEntity.class);
        List<UserEntity> users = query.list();
        tx1.commit();
        session.close();
        return users;
    }
}
