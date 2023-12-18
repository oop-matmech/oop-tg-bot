package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.StatsEntity;
import org.example.db.UserDatabase.models.IStatsMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class StatsDao implements IStatsMethods {
    public StatsEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(StatsEntity.class, id);
    }

    public List<StatsEntity> getUserStats(int userId) {
        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where user='" + userId + "'", StatsEntity.class)
                .list();
        return stats;
    }

    public List<StatsEntity> getAllStats() {
        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity", StatsEntity.class)
                .list();
        return stats;
    }

    public List<StatsEntity> getStatsDay() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now + (24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp < :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    public List<StatsEntity> getStatsWeek() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now + (7L * 24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp < :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    public List<StatsEntity> getStatsMonth() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now + (4L * 7L * 24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp < :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    public void save(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(stats);
        tx1.commit();
        session.close();
    }

    public void update(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(stats);
        tx1.commit();
        session.close();
    }

    public void delete(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(stats);
        tx1.commit();
        session.close();
    }
}
