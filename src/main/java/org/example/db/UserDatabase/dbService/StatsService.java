package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.StatsDao;
import org.example.db.UserDatabase.dbEntities.StatsEntity;

import java.util.*;

public class StatsService {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private Map<String, Integer> toSortedMap(List<StatsEntity> lst) {

        SortedMap<String, Integer> rs = new TreeMap<>();
        lst.forEach(stats -> {
            var statName = stats.getSong().getName();
            rs.merge(statName, 1, Integer::sum);
        });
        return sortByValue(rs);
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


    public Map<String, Integer> getStatsDay() {
        var res = statsDao.getStatsDay();
        var rs = toSortedMap(res);
        return rs;

    }


    public Map<String, Integer> getStatsWeek() {
        var res = statsDao.getStatsWeek();
        var rs = toSortedMap(res);
        return rs;
    }


    public Map<String, Integer> getStatsMonth() {
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
