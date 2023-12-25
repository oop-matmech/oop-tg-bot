package org.example.utils;

import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FormatTracks {
    public String format(ArrayList<GetTopItemTrackEntity> data) {
        if (data.isEmpty()) {
            return "К сожалению, не удалось найти песни.";
        } else {
            return data.stream().map(it ->
                            it.getName() + "\n"
                                    + it.getArtist().name + "\n"
                                    + "Прослушиваний: " + it.getListeners() + "\n-----------\n"
                                    + "URL: " + it.getUrl()
                    )
                    .collect(Collectors.joining("\n\n"));
        }
    }
}
