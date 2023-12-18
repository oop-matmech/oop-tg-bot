import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.example.service.api.ApiBaseConfig;
import org.example.service.api.ApiClient;
import org.example.service.music.service.tracks.topTracks.MusicTopTracks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    private ApiClient apiClient;
    private ApiBaseConfig apiBaseConfig;
    private MusicTopTracks musicTopTracks;

    @BeforeEach
    void init() {
        apiBaseConfig = new ApiBaseConfig();
        apiClient = new ApiClient(apiBaseConfig.okHttpClient);
        musicTopTracks = new MusicTopTracks();
    }

    @DisplayName("Check error status test")
    @Test
    void checkErrorStatus() {
        Request request = new Request.Builder()
                .url(ApiBaseConfig.BASE_URL + "/")
                .build();
        assertEquals(400, apiClient.getResponse(request).code());
    }

    @DisplayName("Tracks test")
    @Test
    void getTopTrackTest() {
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

        assertEquals(200, apiClient.getResponse(request).code());
    }

    @DisplayName("Tracks test without key")
    @Test
    void getTopTrackTestWithoutKey() {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(ApiBaseConfig.BASE_URL + "/")).newBuilder();
        urlBuilder
                .addQueryParameter("method", "chart.gettoptracks")
                .addQueryParameter("format", "json")
                .addQueryParameter("page", "1")
                .addQueryParameter("limit", "10");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        assertEquals(400, apiClient.getResponse(request).code());
    }
}
