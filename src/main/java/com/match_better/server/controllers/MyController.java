package com.match_better.server.controllers;

import com.match_better.server.model.Post;
import com.match_better.server.services.ApiJackson;
import com.match_better.server.services.FakeAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class MyController {

    @Autowired
    private FakeAPI fakeService;
    @Autowired
    private ApiJackson apiJackson;

    @GetMapping("/fetch-post")
    public Post fetchPost() {
        return fakeService.getPost();
    }
    @GetMapping("/fetch-posts")
    public List<Post> fetchPosts() {
        return fakeService.getPosts();
    }
    @GetMapping("/jackson")
    public String testJackson() {
        apiJackson.useJackson();
        return "";
    }

}
