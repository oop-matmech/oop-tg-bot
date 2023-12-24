package org.example.service.entities.tracksEntities;

import com.beust.ah.A;

import java.util.ArrayList;

public class FindTrackEntity {

    public ArrayList<FindTrackEntityExemplar> track;

    public ArrayList<FindTrackEntityExemplar> getTrack() {
        return track;
    }

    public ArrayList<GetTopItemTrackEntity> getTracks() {
        ArrayList<FindTrackEntityExemplar> original = getTrack();
        ArrayList<GetTopItemTrackEntity> result = new ArrayList<>();
        for (FindTrackEntityExemplar elem : original) {
            GetTopItemTrackEntity entity = new GetTopItemTrackEntity();
            entity.setName(elem.name);
            entity.setArtistName(elem.artist);
            entity.setUrl(elem.url);
            entity.setStreamableText(elem.streamable);
            entity.setListeners(Integer.valueOf(elem.listeners));
            entity.setMbid(elem.mbid);
            result.add(entity);
        }
        return result;
    }
}
