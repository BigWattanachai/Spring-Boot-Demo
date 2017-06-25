package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.services.AuthorService;
import com.ascend.springbootdemo.services.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthor() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.create(author), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        return new ResponseEntity<>(authorService.edit(id, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.delete(id), HttpStatus.OK);
    }
}
