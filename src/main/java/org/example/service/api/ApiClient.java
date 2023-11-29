package org.example.service.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {
    private final OkHttpClient okHttpClient;

    public ApiClient(OkHttpClient _okHttpClient) {
        this.okHttpClient = _okHttpClient;
    }

    public Response getResponse(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(String.format("SOMETHING WENT WRONG: %s", e.toString()));
        }
        return response;
    }
}
