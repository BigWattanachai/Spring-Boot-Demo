package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BiG on 6/17/2017 AD.
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler
    public void handleTodoException(AuthorNotFoundException exception,
                                    HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
