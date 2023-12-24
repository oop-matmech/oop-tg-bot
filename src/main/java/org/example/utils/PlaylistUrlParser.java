package org.example.utils;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;

public class PlaylistUrlParser {
    public static String toURL(PlayListEntity playList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("https://bot_rgt.rgt/")
                .append(playList.getName() + "/")
                .append(playList.getId() + "/")
                .append("link");
        return stringBuilder.toString();
    }

    public static PlayListEntity getPlaylistFromUrl(String url) throws NullPointerException {
        PlayListService playListService = new PlayListService();
        var res = url
                .replace("https://bot_rgt.rgt/", "")
                .split("/");
        var playlistId = res[1];
        var playlist = playListService.findById(Integer.parseInt(playlistId));
        if (playlist == null) {
            throw new NullPointerException();
        }
        return playlist;
    }
}
