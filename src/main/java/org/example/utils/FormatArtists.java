package org.example.utils;

import org.example.service.entities.singersEntities.GetTopItemSingersEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FormatArtists {
    public String format(ArrayList<GetTopItemSingersEntity> data) {
        String res = data.stream().map(it ->
                        "NAME: " + it.getName() + "\n"
                                + "ARTIST: " + it.getName() + "\n"
                                + "URL: " + it.getUrl() + "\n"
                                + "PLAYCOUNT: " + it.getPlaycount() + "\n"
                                + "LISTENERS: " + it.getListeners()
                )
                .collect(Collectors.joining("\n"));
        return res;
    }
}
