package com.ascend.springbootdemo.repositories;

import com.ascend.springbootdemo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BiG on 6/21/2017 AD.
 */
public interface PostRepo extends JpaRepository<Post, Long> {
}
