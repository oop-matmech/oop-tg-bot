package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.models.IPlaylistMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayListDao implements IPlaylistMethods {
    public PlayListEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(PlayListEntity.class, id);
    }

    public PlayListEntity findByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<PlayListEntity> query = session.createQuery("From PlayListEntity where name='" + name + "'", PlayListEntity.class);
        PlayListEntity playList = query.getSingleResultOrNull();
        tx1.commit();
        session.close();
        return playList;
    }

    public int save(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        int id = (int) session.save(playlist);
        tx1.commit();
        session.close();
        return id;
    }

    public void update(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(playlist);
        tx1.commit();
        session.close();
    }

    public void delete(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(playlist);
        tx1.commit();
        session.close();
    }

    public List<PlayListEntity> findAll() {
        List<PlayListEntity> playlists = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From PlayListEntity", PlayListEntity.class)
                .list();
        return playlists;
    }

}
