package org.example.service.music.service.tracks.topTracks;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.api.ApiBaseConfig;
import org.example.service.api.ApiClient;
import org.example.service.api.Mapper;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;
import org.example.service.entities.tracksEntities.GetTopTracksEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MusicTopTracks implements IMusicTopTracks {
    private final ApiBaseConfig apiBaseConfig = new ApiBaseConfig();
    private final ApiClient apiClient = new ApiClient(apiBaseConfig.okHttpClient);
    private final Mapper<GetTopTracksEntity> mapper = new Mapper<>();

    @Override
    public ArrayList<GetTopItemTrackEntity> getTopTracks() {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiBaseConfig.BASE_URL + "/")).newBuilder();
        urlBuilder
                .addQueryParameter("method", "chart.gettoptracks")
                .addQueryParameter("api_key", ApiBaseConfig.apikey)
                .addQueryParameter("format", "json")
                .addQueryParameter("page", "1")
                .addQueryParameter("limit", "10");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response rsp = apiClient.getResponse(request)) {
            GetTopTracksEntity getTopTracsEntity = mapper.mapObject(rsp.body().string(), GetTopTracksEntity.class);
            ArrayList<GetTopItemTrackEntity> res = getTopTracsEntity.getTracks().track;
            return res;
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return new ArrayList<GetTopItemTrackEntity>();
        }
    }
}
