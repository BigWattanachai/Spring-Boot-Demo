package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@RestController
@RequestMapping("/api/v1")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthor() {
        return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(Author author) {
        return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
    }


}
