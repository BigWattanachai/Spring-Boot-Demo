package com.ascend.springbootdemo.repositories;

import com.ascend.springbootdemo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by BiG on 6/17/2017 AD.
 */
public interface AuthorRepo extends JpaRepository<Author, Long> {
}
