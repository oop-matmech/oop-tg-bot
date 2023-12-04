package org.example.db.UserDatabase.dbEntities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "tg_id", unique = true)
    private String tg_id;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<PlayListEntity> playlists;

    public UserEntity() {
    }

    public UserEntity(String name, String tg_id) {
        this.name = name;
        this.tg_id = tg_id;
        this.playlists = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<PlayListEntity> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayListEntity> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(PlayListEntity playList) {
        playList.setUser(this);
        this.playlists.add(playList);
    }


    public void removePlaylist(int playListId) {
        this.playlists.remove(playListId);
    }

    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tg_id=" + tg_id +
                '}';
    }
}
