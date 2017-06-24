package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Author createAuthor(Author author) {
        return authorRepo.saveAndFlush(author);
    }

    public Author getAuthorById(Long id) {
        return Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
    }

    public Author deleteAuthorById(Long id) {
        Author author = Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
        authorRepo.delete(author);
        authorRepo.flush();
        return author;
    }

    public Author updateAuthor(long id, Author authorUpdate) {
        Author author = Optional.ofNullable(authorRepo.findOne(id)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), id)));
        author.setFirstName(authorUpdate.getFirstName());
        author.setLastName(authorUpdate.getLastName());

        return authorRepo.saveAndFlush(author);
    }
}
