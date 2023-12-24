package org.example.db.UserDatabase.dbService;

import org.example.db.UserDatabase.dao.StatsDao;
import org.example.db.UserDatabase.dbEntities.StatsEntity;

import java.util.*;

/**
 * Интерфейс работы с сущностями таблицы статистики.
 * Сущности описывают лайки, поставленные пользователями песням
 */
public class StatsService {

    public class CountEntity {
        public StatsEntity stats;
        public String counter;

        public CountEntity(StatsEntity stats, String counter) {
            this.stats = stats;
            this.counter = counter;
        }
    }
    /**
     * Метод сортировки map по значению
     * @param map объект сортировки
     * @param <K> имя + url песни
     * @param <V> количество лайков на песню
     * @return LinkedHashMap - отсортированный map по значениям
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * Возвращает отсортированный map лайков,
     * ключ - имя + url песни,
     * значение - кол-во лайков на эту песню.
     * @param lst список сущностей статитстики
     * @return отсортированный map через sortByValue
     */
    private Map<String, Integer> toSortedMap(List<StatsEntity> lst) {

        SortedMap<String, Integer> rs = new TreeMap<>();
        lst.forEach(stats -> {
            String name = stats.getSong().getName();
            rs.merge(name, 1, Integer::sum);
        });
        return sortByValue(rs);
    }

    /**
     * экземпляр класса statsDao, используемый для работы с его методами
     */
    private final StatsDao statsDao = new StatsDao();

    /**
     * Поиск лайка по id
     * @param id id в таблице статистики
     * @return StatsEntity - конкретная запись
     */
    public StatsEntity findById(int id) {
        return statsDao.findById(id);
    }

    /**
     * Поиск всех лайков (сущностей статистики) от конкретного пользователя
     * @param userId id пользователя
     * @return List<StatsEntity> - список всех лайков, поставленных пользователем
     */
    public List<StatsEntity> getUserStats(int userId) {
        return statsDao.getUserStats(userId);
    }

    /**
     * @return List<StatsEntity> - все лайки в таблице
     */
    public List<StatsEntity> getAllStats() {
        return statsDao.getAllStats();
    }

    /**
     * Формирование чарта за последние 24 часа от момента вызова метода.
     * @return map<StatsEntity> - чарт за день, отсортирован по убыванию  количества лайков
     */
    public ArrayList<CountEntity> getStatsDay() {
        ArrayList<CountEntity> result = new ArrayList<>();
        var songs = statsDao.getStatsDay();
        var res = toSortedMap(songs);
        var rs = res.entrySet().toArray();
        int idx = 1;
        for (int i = rs.length - 1; i >= 0; i--) {
            if (idx == 11) {
                break;
            } else {
                var newS = rs[i].toString().split("=");
                for (StatsEntity elem : songs) {
                    if (elem.getSong().getName().equals(newS[0])) {
                        CountEntity ce = new CountEntity(elem, newS[1]);
                        result.add(ce);
                        break;
                    }
                }
                idx++;
            }
        }
        return result;
    }


    /**
     * Формирование чарта за последнюю неделю от момента вызова метода.
     * @return map<StatsEntity> - чарт за неделю, отсортирован по убыванию  количества лайков
     */
    public ArrayList<CountEntity> getStatsWeek() {
        ArrayList<CountEntity> result = new ArrayList<>();
        var songs = statsDao.getStatsWeek();
        var res = toSortedMap(songs);
        var rs = res.entrySet().toArray();
        int idx = 1;
        for (int i = rs.length - 1; i >= 0; i--) {
            if (idx == 11) {
                break;
            } else {
                var newS = rs[i].toString().split("=");
                for (StatsEntity elem : songs) {
                    if (elem.getSong().getName().equals(newS[0])) {
                        CountEntity ce = new CountEntity(elem, newS[1]);
                        result.add(ce);
                        break;
                    }
                }
                idx++;
            }
        }
        return result;
    }

    /**
     * Формирование чарта за последний месяц от момента вызова метода.
     * @return map<StatsEntity> - чарт за месяц, отсортирован по убыванию  количества лайков
     */
    public ArrayList<CountEntity> getStatsMonth() {
        ArrayList<CountEntity> result = new ArrayList<>();
        var songs = statsDao.getStatsMonth();
        var res = toSortedMap(songs);
        var rs = res.entrySet().toArray();
        int idx = 1;
        for (int i = rs.length - 1; i >= 0; i--) {
            if (idx == 11) {
                break;
            } else {
                var newS = rs[i].toString().split("=");
                for (StatsEntity elem : songs) {
                    if (elem.getSong().getName().equals(newS[0])) {
                        CountEntity ce = new CountEntity(elem, newS[1]);
                        result.add(ce);
                        break;
                    }
                }
                idx++;
            }
        }
        return result;
    }

    /**
     * Сохранение записи лайка в таблице
     * @param stats запись, добавляемая в таблицу
     */
    public void save(StatsEntity stats) {
        statsDao.save(stats);
    }

    /**
     * Обновление записи лайка в таблице
     * @param stats запись, обновляемая в таблице
     */
    public void update(StatsEntity stats) {
        statsDao.update(stats);
    }

    /**
     * Удаление записи лайка в таблице
     * @param stats удаление записи из таблицы
     */
    public void delete(StatsEntity stats) {
        statsDao.delete(stats);
    }
}
