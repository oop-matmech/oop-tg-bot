package org.example.service.music.service.singers.topSingers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.api.ApiBaseConfig;
import org.example.service.entities.singersEntities.GetTopItemSingersEntity;
import org.example.service.entities.singersEntities.GetTopSingersEntity;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;
import org.example.service.entities.tracksEntities.GetTopTracksEntity;
import org.example.service.music.service.tracks.topTracks.IMusicTopTracks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MusicTopSingers implements IMusicTopSingers {
    public ApiBaseConfig apiBaseConfig = new ApiBaseConfig();

    @Override
    public ArrayList<GetTopItemSingersEntity> getTopArtists() {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiBaseConfig.BASE_URL + "/")).newBuilder();
        urlBuilder
                .addQueryParameter("method", "chart.gettopartists")
                .addQueryParameter("api_key", ApiBaseConfig.apikey)
                .addQueryParameter("format", "json")
                .addQueryParameter("page", "1")
                .addQueryParameter("limit", "10");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = apiBaseConfig.okHttpClient.newCall(request).execute()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            GetTopSingersEntity getTopArtistsEntity = objectMapper.readValue(response.body().string(), GetTopSingersEntity.class);
            ArrayList<GetTopItemSingersEntity> res = getTopArtistsEntity.getSingers().artist;
            return res;

        } catch (IOException e) {
            System.out.println(String.format("SOMETHING WENT WRONG: %s", e.toString()));
            return new ArrayList<GetTopItemSingersEntity>();
        }
    }
}
