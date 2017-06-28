package com.ascend.springbootdemo.exceptions;

/**
 * Created by BiG on 6/17/2017 AD.
 */
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }

}
