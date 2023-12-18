package org.example.db.UserDatabase.dbEntities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "songs")
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private double duration;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name = "artistName")
    private String artistName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "playlistSongs",
            joinColumns = {@JoinColumn(name = "songId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "playlistId", referencedColumnName = "id")})
    private Set<PlayListEntity> playlists;

    @OneToMany(
            mappedBy = "song",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<StatsEntity> stats;

    public List<StatsEntity> getStats() {
        return stats;
    }

    public void setStats(List<StatsEntity> stats) {
        this.stats = stats;
    }

    public SongEntity() {
    }

    public SongEntity(String name, double duration, String url, String artistName) {
        this.name = name;
        this.duration = duration;
        this.url = url;
        this.artistName = artistName;
        this.playlists = new HashSet<>();
        this.stats = new ArrayList<>();
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

    public void addPlaylist(PlayListEntity playList) {
        playlists.add(playList);
    }

    public void removePlaylist(PlayListEntity playList) {
        playlists.remove(playList);
    }

    public void setPlaylists(Set<PlayListEntity> playlists) {
        this.playlists = playlists;
    }

    public String toString() {
        String res = "name = " + name + " url = " + url + " artistName = " + artistName;
        return res;
    }
}
