package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.models.ISongMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SongDao implements ISongMethods {
    public SongEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(SongEntity.class, id);
    }

    public SongEntity findByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<SongEntity> query = session.createQuery("From SongEntity where name='" + name + "'", SongEntity.class);
        SongEntity song = query.getSingleResultOrNull();
        tx1.commit();
        session.close();
        return song;
    }

    public SongEntity findByUrl(String url) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<SongEntity> query = session.createQuery("From SongEntity where url='" + url + "'", SongEntity.class);
        SongEntity song = query.getSingleResultOrNull();
        tx1.commit();
        session.close();
        return song;
    }

    public void save(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(song);
        tx1.commit();
        session.close();
    }

    public void update(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(song);
        tx1.commit();
        session.close();
    }

    public void delete(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(song);
        tx1.commit();
        session.close();
    }
}
