package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.entities.Post;
import com.ascend.springbootdemo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BiG on 6/24/2017 AD.
 */
@RestController
@RequestMapping("/api/v1")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/authors/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable(value = "id") Long authorId, @RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(authorId, post), HttpStatus.CREATED);
    }

    @GetMapping("/authors/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long authorId) {
        return new ResponseEntity<>(postService.getPostById(authorId), HttpStatus.OK);
    }
}
