package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BiG on 6/17/2017 AD.
 */

@ControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler
    public void handleAuthorNotFoundException(AuthorNotFoundException exception,
                                              HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }


    @ExceptionHandler
    public void handlePostNotFoundException(PostNotFoundException exception,
                                            HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
