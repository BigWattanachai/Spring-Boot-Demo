package com.ascend.springbootdemo.constants;

import lombok.Getter;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@Getter
public enum ErrorMsgEnum {
    AUTHOR_NOT_FOUND("Author id %s was not found"),
    POST_NOT_FOUND("Post id %s was not found");
    private final String msg;

    ErrorMsgEnum(String msg) {
        this.msg = msg;
    }
}
