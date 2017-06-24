package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.entities.Post;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.exceptions.PostNotFoundException;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import com.ascend.springbootdemo.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by BiG on 6/24/2017 AD.
 */
@Service
public class PostService {
    private AuthorRepo authorRepo;
    private PostRepo postRepo;

    @Autowired
    public PostService(AuthorRepo authorRepo, PostRepo postRepo) {
        this.authorRepo = authorRepo;
        this.postRepo = postRepo;
    }

    public Post createPost(Long authorId, Post post) {
        Author author = Optional.ofNullable(authorRepo.findOne(authorId)).orElseThrow(() ->
                new AuthorNotFoundException(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), authorId)));
        post.setAuthor(author);
        return postRepo.saveAndFlush(post);
    }

    public Post getPostById(Long id) {
        return Optional.ofNullable(postRepo.findOne(id)).orElseThrow(() ->
                new PostNotFoundException(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), id)));
    }
}
