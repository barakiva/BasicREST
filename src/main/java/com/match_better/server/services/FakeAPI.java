package com.match_better.server.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.match_better.server.model.Post;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeAPI {

    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    private String FAKE_URL = "https://jsonplaceholder.typicode.com";

    public Post getPost() {
        String post_param = "/posts/1";
        String response = this.makeRequest(FAKE_URL + post_param);

        Post post  = gson.fromJson(response, Post.class);
        String json = gson.toJson(post);

        return post;

    }
    public List<Post> getPosts() {
        String params =  "/posts";
        String response = this.makeRequest(FAKE_URL + params);

        Type collectionType = new TypeToken<ArrayList<Post>>(){}.getType();
        List<Post> posts = gson.fromJson(response, collectionType);

        return posts;
    }


    private String makeRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = null;
        String jsonData = "";
        try {
            response = call.execute();
            jsonData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
