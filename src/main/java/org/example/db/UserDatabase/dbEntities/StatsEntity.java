package org.example.db.UserDatabase.dbEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/** Сущность таблицы статистики - лайк.
 *
 *
 */
@Entity
@Table(name = "stats")
public class StatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "songId")
    private SongEntity song;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    /** Конструктор лайка
     * <p>
     * при вызове сохраняются поля timestamp и modifydate - время
     * создания лайка и время изменения лайка соответственно.
     * @param user сущность пользователя
     * @param song сущность песни
     */
    public StatsEntity(UserEntity user, SongEntity song) {
        this.user = user;
        this.song = song;
        this.timestamp = new Date();
        this.modifyDate = new Date();
    }

    public StatsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SongEntity getSong() {
        return song;
    }

    public void setSong(SongEntity song) {
        this.song = song;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
