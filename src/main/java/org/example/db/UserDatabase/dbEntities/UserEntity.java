package org.example.db.UserDatabase.dbEntities;

import java.util.List;

public class UserEntity {
    private Integer id;
    private String name;
    private String tg_id;
    private List<Integer> fk_playlists_ids;

    public UserEntity(Integer _id, String _name, String _tg_id, List<Integer> _fk_playlists_ids) {
        this.id = _id;
        this.fk_playlists_ids = _fk_playlists_ids;
        this.tg_id = _tg_id;
        this.name = _name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTg_id() {
        return tg_id;
    }

    public void setTg_id(String tg_id) {
        this.tg_id = tg_id;
    }

    public List<Integer> getFk_playlists_ids() {
        return fk_playlists_ids;
    }

    public void setFk_playlists_ids(List<Integer> fk_playlists_ids) {
        this.fk_playlists_ids = fk_playlists_ids;
    }
}
