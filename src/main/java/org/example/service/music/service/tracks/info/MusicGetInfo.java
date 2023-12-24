package org.example.service.music.service.tracks.info;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.api.ApiBaseConfig;
import org.example.service.api.ApiClient;
import org.example.service.api.FindMapper;
import org.example.service.entities.tracksEntities.FindTrackEntity;
import org.example.service.entities.tracksEntities.GetTopItemTrackEntity;

import java.io.IOException;
import java.util.Objects;

/**
 * @see www.last.fm/api/show/track.getInfo
 */
public class MusicGetInfo implements IMusicGetInfo {
    private final ApiBaseConfig apiBaseConfig = new ApiBaseConfig();
    private final ApiClient apiClient = new ApiClient(apiBaseConfig.okHttpClient);
    private final FindMapper<FindTrackEntity> mapper = new FindMapper<>();

    public GetTopItemTrackEntity getTrackInfo(String trackName, String artistName) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiBaseConfig.BASE_URL + "/")).newBuilder();
        urlBuilder
                .addQueryParameter("method", "track.getInfo")
                .addQueryParameter("api_key", ApiBaseConfig.apikey)
                .addQueryParameter("artist", artistName)
                .addQueryParameter("track", trackName)
                .addQueryParameter("format", "json");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response rsp = apiClient.getResponse(request)) {
            String value = rsp.body().string();
            value = value.substring(value.indexOf("track") + 7);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            GetTopItemTrackEntity result = objectMapper.readValue(value, GetTopItemTrackEntity.class);
            return result;
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return new GetTopItemTrackEntity();
        }
    }
}
