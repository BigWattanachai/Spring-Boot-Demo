package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.entities.Author;

import java.util.List;

/**
 * Created by BiG on 6/25/2017 AD.
 */
public interface AuthorService extends GenericService<Author> {
    List<Author> getAll();

    Author create(Author author);
}
