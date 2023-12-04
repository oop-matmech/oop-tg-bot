package org.example.db.UserDatabase.dao;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.db.UserDatabase.models.IPlaylistMethods;
import org.example.db.UserDatabase.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class PlayListDao implements IPlaylistMethods {
    private final SongsService songsService = new SongsService();

    public PlayListEntity findById(int id) {
        return HibernateUtils.getSessionFactory().openSession().get(PlayListEntity.class, id);
    }

    public List<PlayListEntity> findByName(String name) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query<PlayListEntity> query = session.createQuery("From PlayListEntity where name='" + name + "'", PlayListEntity.class);
        List<PlayListEntity> playList = query.getResultList();
        tx1.commit();
        session.close();
        return playList;
    }

    public int save(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        int id = (int) session.save(playlist);
        tx1.commit();
        session.close();
        return id;
    }

    public void update(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(playlist);
        tx1.commit();
        session.close();
    }

    public void delete(PlayListEntity playlist) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(playlist);
        tx1.commit();
        session.close();
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, SongEntity song) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        var playList = session.get(PlayListEntity.class, playlistId);
        playList.addSong(song);
        session.merge(playList);
        tx1.commit();
        session.close();
        return true;

    }

    public List<PlayListEntity> findAll() {
        List<PlayListEntity> playlists = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From PlayListEntity", PlayListEntity.class)
                .list();
        return playlists;
    }

    public List<PlayListEntity> getUserPlaylists(int userId) {
        List<PlayListEntity> playlists = HibernateUtils
                .getSessionFactory()
                .openSession()
                .createQuery("From PlayListEntity where user='" + userId + "'", PlayListEntity.class)
                .list();
        return playlists;
    }

    public Set<SongEntity> getSongsFromPlaylist(int playlistId) {
        PlayListEntity playList = findById(playlistId);
        Set<SongEntity> songs = playList.getSongs();
        return songs;
    }

}
