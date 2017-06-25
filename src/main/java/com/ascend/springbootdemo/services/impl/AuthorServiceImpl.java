package com.ascend.springbootdemo.services.impl;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import com.ascend.springbootdemo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getById(Long id) {
        return Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
    }

    @Override
    public Author create(Author author) {
        return authorRepo.saveAndFlush(author);
    }

    @Override
    public Author edit(Long id, Author authorUpdate) {
        Author author = Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
        author.setFirstName(authorUpdate.getFirstName());
        author.setLastName(authorUpdate.getLastName());

        return authorRepo.saveAndFlush(author);
    }

    @Override
    public Author delete(Long id) {
        Author author = Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
        authorRepo.delete(author);
        authorRepo.flush();
        return author;
    }

    @Override
    public List<Author> getAll() {
        return authorRepo.findAll();
    }
}
