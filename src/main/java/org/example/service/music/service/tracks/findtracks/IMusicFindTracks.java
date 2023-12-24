package org.example.service.music.service.tracks.findtracks;

import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;

import java.util.ArrayList;

public interface IMusicFindTracks {
    public ArrayList<GetTopItemTrackEntity> getTracksFoundByName(String trackName, String limit);
}
