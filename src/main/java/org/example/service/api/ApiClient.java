package org.example.service.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {

    public Response getResponse(Request request, OkHttpClient okHttpClient) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(String.format("SOMETHING WENT WRONG: %s", e.toString()));
        }
        return response;
    }
}
