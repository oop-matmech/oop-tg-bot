package org.example.db.UserDatabase.dbEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class PlayListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "playlistSongs",
            joinColumns = @JoinColumn(name = "songId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "playlistId", referencedColumnName = "id"))
    private Set<SongEntity> songs;

    public PlayListEntity() {
    }

    public PlayListEntity(String name, String description, UserEntity user) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.songs = new HashSet<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void addSong(SongEntity song) {

    }

    public Set<SongEntity> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongEntity> songs) {
        this.songs = songs;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}