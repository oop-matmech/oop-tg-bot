package org.example.service.music;

import org.example.service.entities.singersEntities.GetTopItemSingersEntity;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;

import java.util.ArrayList;

public interface IMusicApi {
    public void auth();
    public ArrayList<GetTopItemTrackEntity> getTopTracks();
    public ArrayList<GetTopItemSingersEntity> getTopArtists();
}
