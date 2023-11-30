package org.example.db.UserDatabase.models;

import org.example.db.UserDatabase.dbEntities.SongEntity;

public interface ISongMethods {
    public SongEntity findById(int id);

    public SongEntity findByName(String name);

    public void save(SongEntity song);

    public void update(SongEntity song);

    public void delete(SongEntity song);
}
