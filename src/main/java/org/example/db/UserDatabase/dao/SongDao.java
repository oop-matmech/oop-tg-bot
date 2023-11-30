package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.models.ISongMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SongDao implements ISongMethods {
    public SongEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(SongEntity.class, id);
    }

    public SongEntity findByName(String name) {
        return HibernateUtils.getSessionFactory().openSession().get(SongEntity.class, name);
    }

    public void save(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(song);
        tx1.commit();
        session.close();
    }

    public void update(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(song);
        tx1.commit();
        session.close();
    }

    public void delete(SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(song);
        tx1.commit();
        session.close();
    }
}
