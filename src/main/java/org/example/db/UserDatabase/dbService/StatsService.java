package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.StatsDao;
import org.example.db.UserDatabase.dbEntities.StatsEntity;

import java.util.*;

public class StatsService {

    private SortedMap<String, Integer> toSortedMap(List<StatsEntity> lst) {
        SortedMap<String, Integer> rs = new TreeMap<>();
        lst.forEach(stats -> {
            var statName = stats.getSong().getName();
            rs.merge(statName, 1, Integer::sum);
        });
        return rs;
    }

    private final StatsDao statsDao = new StatsDao();


    public StatsEntity findById(int id) {
        return statsDao.findById(id);
    }


    public List<StatsEntity> getUserStats(int userId) {
        return statsDao.getUserStats(userId);
    }


    public List<StatsEntity> getAllStats() {
        return statsDao.getAllStats();
    }


    public SortedMap<String, Integer> getStatsDay() {
        var res = statsDao.getStatsDay();
        var rs = toSortedMap(res);
        return rs;

    }


    public SortedMap<String, Integer> getStatsWeek() {
        var res = statsDao.getStatsWeek();
        var rs = toSortedMap(res);
        return rs;
    }


    public SortedMap<String, Integer> getStatsMonth() {
        var res = statsDao.getStatsMonth();
        var rs = toSortedMap(res);
        return rs;
    }


    public void save(StatsEntity stats) {
        statsDao.save(stats);
    }


    public void update(StatsEntity stats) {
        statsDao.update(stats);
    }


    public void delete(StatsEntity stats) {
        statsDao.delete(stats);
    }
}
