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
    public ApiBaseConfig apiBaseConfig = new ApiBaseConfig();

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

        ApiClient apiClient = new ApiClient();
        Mapper<GetTopTracksEntity> mapper = new Mapper<>();
        try (Response rsp = apiClient.getResponse(request, apiBaseConfig.okHttpClient)) {
            GetTopTracksEntity getTopTracsEntity = mapper.mapObject(rsp.body().string(), GetTopTracksEntity.class);
            ArrayList<GetTopItemTrackEntity> res = getTopTracsEntity.getTracks().track;
            return res;
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return new ArrayList<GetTopItemTrackEntity>();
        }
    }
}
