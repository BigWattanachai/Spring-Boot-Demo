package com.ascend.springbootdemo.exceptions;

/**
 * Created by BiG on 6/17/2017 AD.
 */

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException() {
        this("Author Not Found");
    }
}
