package org.example.service.entities.singersEntities;

public class GetTopItemSingersEntity {
    protected String name;
    protected Integer playcount;
    protected Integer listeners;
    protected String mbid;
    protected String url;
    protected Integer streamable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStreamable() {
        return streamable;
    }

    public void setStreamable(Integer streamable) {
        this.streamable = streamable;
    }
}
