package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@Service
public class AuthorService {
    private AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAllAuthor() {
        return authorRepo.findAll();
    }
}
