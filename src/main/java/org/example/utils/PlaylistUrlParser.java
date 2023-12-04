package org.example.utils;

import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbService.PlayListService;

public class PlaylistUrlParser {
    public static String toURL(PlayListEntity playList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("https://bot.com/")
                .append(playList.getName() + "/")
                .append(playList.getId() + "/")
                .append("link");
        return stringBuilder.toString();
    }

    public static PlayListEntity getPlaylistFromUrl(String url) {
        PlayListService playListService = new PlayListService();
        var res = url
                .replace("https://bot.com/", "")
                .split("/");
        var playlistId = res[1];
        var playlist = playListService.findById(Integer.parseInt(playlistId));
        return playlist;
    }
}
