package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.models.IPlaylistMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayListDao implements IPlaylistMethods {
    public PlayListEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(PlayListEntity.class, id);
    }

    public PlayListEntity findByName(String name) {
        return HibernateUtils.getSessionFactory().openSession().get(PlayListEntity.class, name);
    }

    public void save(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(playlist);
        tx1.commit();
        session.close();
    }

    public void update(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(playlist);
        tx1.commit();
        session.close();
    }

    public void delete(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(playlist);
        tx1.commit();
        session.close();
    }

    public List<PlayListEntity> findAll() {
        List<PlayListEntity> playlists = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From playlists", PlayListEntity.class)
                .list();
        return playlists;
    }

}
