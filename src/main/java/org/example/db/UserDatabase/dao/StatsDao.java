package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.StatsEntity;
import org.example.db.UserDatabase.models.IStatsMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/** Класс методов работы с базой данных для сущностей
 *  класса "Статистики" (лайков)
 */
public class StatsDao {
    /**
     * Поиск записи по id
     * @param id id в таблице статистики
     * @return StatsEntity - конкретная запись
     */
    public StatsEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(StatsEntity.class, id);
    }

    /**
     * Поиск всех лайков (сущностей статистики) от конкретного пользователя
     * @param userId id пользователя
     * @return List<StatsEntity> - список всех лайков, поставленных пользователем
     */
    public List<StatsEntity> getUserStats(int userId) {
        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where user='" + userId + "'", StatsEntity.class)
                .list();
        return stats;
    }

    /**
     * @return List<StatsEntity> - список всех лайков в таблице
     */
    public List<StatsEntity> getAllStats() {
        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity", StatsEntity.class)
                .list();
        return stats;
    }

    /**
     * @return List<StatsEntity> - список всех лайков за день
     */
    public List<StatsEntity> getStatsDay() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now - (24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp > :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    /**
     * @return List<StatsEntity> - список всех лайков за неделю
     */
    public List<StatsEntity> getStatsWeek() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now - (7L * 24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp > :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    /**
     * @return List<StatsEntity> - список всех лайков за месяц
     */
    public List<StatsEntity> getStatsMonth() {
        long now = System.currentTimeMillis();
        long nowMinus24h = now - (4L * 7L * 24L * 60L * 60L * 1000L);
        Timestamp limit = new Timestamp(nowMinus24h);

        List<StatsEntity> stats = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From StatsEntity where timestamp > :limit", StatsEntity.class)
                .setParameter("limit", limit)
                .list();
        return stats;
    }

    /**
     * Сохранение записи лайка в таблице
     * @param stats запись, добавляемая в таблицу
     */
    public void save(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(stats);
        tx1.commit();
        session.close();
    }

    /**
     * Обновление записи лайка в таблице
     * @param stats запись, обновляемая в таблице
     */
    public void update(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(stats);
        tx1.commit();
        session.close();
    }

    /**
     * Удаление записи лайка в таблице
     * @param stats удаление записи из таблицы
     */
    public void delete(StatsEntity stats) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(stats);
        tx1.commit();
        session.close();
    }
}
