package org.example.service.music.service.tracks.info;

import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;

import java.util.ArrayList;


/**
 * @see www.last.fm/api/show/track.getInfo
 */
public interface IMusicGetInfo {
    public GetTopItemTrackEntity getTrackInfo(String trackName, String artistName);

}
