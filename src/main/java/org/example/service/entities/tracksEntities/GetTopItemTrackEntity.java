package org.example.service.entities.tracksEntities;
public class GetTopItemTrackEntity {

    public GetTopItemTrackEntity(String name, Integer duration, Integer playcount, Integer listeners, String mbid, String url, Streamable streamable, Artist artist) {
        this.name = name;
        this.duration = duration;
        this.playcount = playcount;
        this.listeners = listeners;
        this.mbid = mbid;
        this.url = url;
        this.streamable = streamable;
        this.artist = artist;
    }

    public GetTopItemTrackEntity() {
        this.name = "";
        this.duration = null;
        this.playcount = null;
        this.listeners = 0;
        this.mbid = "";
        this.url = "";
        this.streamable = new Streamable();
        this.artist = new Artist();
    }

    public class Streamable {
        public String text;
        public Integer fulltrack;
    }

    public class Artist {
        public Artist() {
            this.name = null;
            this.mbid = null;
            this.url = null;
        }

        public String name;
        public String mbid;
        public String url;
    }

    protected String name;
    protected Integer duration;
    protected Integer playcount;
    protected Integer listeners;
    protected String mbid;
    protected String url;
    protected Streamable streamable;
    protected Artist artist;

    public String getName() {
        return name;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public Integer getListeners() {
        return listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtistName(String name) {
        artist.name = name;
    }

    public void setStreamableText(String text) {
        streamable.text = text;
    }
}
