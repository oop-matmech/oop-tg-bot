package org.example.db.UserDatabase.models;

import org.example.db.UserDatabase.dbEntities.StatsEntity;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.List;

public interface IStatsMethods {
    public StatsEntity findById(int id);

    public List<StatsEntity> getUserStats(int userId) ;

    public List<StatsEntity> getAllStats();

    public List<StatsEntity> getStatsDay() ;

    public List<StatsEntity> getStatsWeek() ;

    public List<StatsEntity> getStatsMonth() ;

    public void save(StatsEntity stats);

    public void update(StatsEntity stats) ;

    public void delete(StatsEntity stats) ;
}
