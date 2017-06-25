package com.ascend.springbootdemo.services.impl;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.entities.Post;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.exceptions.PostNotFoundException;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import com.ascend.springbootdemo.repositories.PostRepo;
import com.ascend.springbootdemo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by BiG on 6/24/2017 AD.
 */
@Service
public class PostServiceImpl implements PostService {
    private AuthorRepo authorRepo;
    private PostRepo postRepo;

    @Autowired
    public PostServiceImpl(AuthorRepo authorRepo, PostRepo postRepo) {
        this.authorRepo = authorRepo;
        this.postRepo = postRepo;
    }

    @Override
    public Post getById(Long id) {
        return Optional.ofNullable(postRepo.findOne(id)).orElseThrow(() ->
                new PostNotFoundException(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), id)));
    }

    @Override
    public Post create(Long authorId, Post post) {
        Author author = Optional.ofNullable(authorRepo.findOne(authorId)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), authorId)));
        post.setAuthor(author);

        return postRepo.saveAndFlush(post);
    }

    @Override
    public Post edit(Long id, Post postUpdate) {
        Post post = Optional.ofNullable(postRepo.findOne(id)).orElseThrow(() ->
                new PostNotFoundException(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), id)));
        post.setContent(postUpdate.getContent());

        return postRepo.saveAndFlush(post);
    }

    @Override
    public Post delete(Long id) {
        Post post = Optional.ofNullable(postRepo.findOne(id)).orElseThrow(() ->
                new PostNotFoundException(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), id)));
        postRepo.delete(post);
        postRepo.flush();

        return post;
    }
}
