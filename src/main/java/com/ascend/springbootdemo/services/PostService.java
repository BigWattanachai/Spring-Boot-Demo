package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.entities.Post;

/**
 * Created by BiG on 6/25/2017 AD.
 */
public interface PostService extends GenericService<Post> {
    Post create(Long authorId, Post post);
}
