package com.match_better.server.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.match_better.server.model.Post;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiJackson {
    private OkHttpClient client = new OkHttpClient();
    private String BASE_URL = "https://jsonplaceholder.typicode.com";
    private String params = "/posts/1";

    public void useJackson() {

        Request request = new Request.Builder()
                .url(BASE_URL + params)
                .build();
        Call call = client.newCall(request);
        Response response = null;
        String jsonData = "";
        Post post = null;

        String jsonChild = "";
        ObjectMapper oMapper = new ObjectMapper();
        try {
            response = call.execute();
            jsonData = response.body().string();
            post = oMapper.readValue(jsonData, Post.class);
            jsonChild = oMapper.writeValueAsString(post);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(post.getId() + " and " + post.getTitle());
        System.out.println(jsonChild);
    }

}
