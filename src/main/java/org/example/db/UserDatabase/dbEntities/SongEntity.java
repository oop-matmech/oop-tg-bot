package org.example.db.UserDatabase.dbEntities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private double duration;

    @Column(name = "url")
    private String url;

    @Column(name = "artistName")
    private String artistName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "playlistSongs",
            joinColumns = {@JoinColumn(name = "playlistId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "songId", referencedColumnName = "id")})
    private Set<PlayListEntity> playlists;

    public SongEntity() {
    }
    public SongEntity(String name, double duration, String url, String artistName) {
        this.name = name;
        this.duration = duration;
        this.url = url;
        this.artistName = artistName;
        this.playlists = new HashSet<>();
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Set<PlayListEntity> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<PlayListEntity> playlists) {
        this.playlists = playlists;
    }

}
