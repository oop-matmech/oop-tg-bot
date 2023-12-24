package org.example.service.music.service.tracks.findtracks;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.api.ApiBaseConfig;
import org.example.service.api.ApiClient;
import org.example.service.api.FindMapper;
import org.example.service.api.Mapper;
import org.example.service.entities.tracksEntities.FindTrackEntity;
import org.example.service.entities.tracksEntities.FindTrackEntityExemplar;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;
import org.example.service.entities.tracksEntities.GetTopTracksEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MusicFindTracks implements IMusicFindTracks {
    private final ApiBaseConfig apiBaseConfig = new ApiBaseConfig();
    private final ApiClient apiClient = new ApiClient(apiBaseConfig.okHttpClient);
    private final FindMapper<FindTrackEntity> mapper = new FindMapper<>();

    @Override
    public ArrayList<GetTopItemTrackEntity> getTracksFoundByName(String trackName) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiBaseConfig.BASE_URL + "/")).newBuilder();
        urlBuilder
                .addQueryParameter("method", "track.search")
                .addQueryParameter("track", trackName)
                .addQueryParameter("api_key", ApiBaseConfig.apikey)
                .addQueryParameter("format", "json")
                .addQueryParameter("page", "1")
                .addQueryParameter("limit", "10");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response rsp = apiClient.getResponse(request)) {
            FindTrackEntity foundTracks = mapper.mapObject(rsp.body().string(), FindTrackEntity.class);
            return foundTracks.getTracks();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return new ArrayList<GetTopItemTrackEntity>();
        }
    }
}
