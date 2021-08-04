package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.CommonResponseBody;

import lombok.Getter;

@Getter
public abstract class RequestFailException extends RuntimeException {
    private String devMessage;
    private String displayMessage;


    public RequestFailException(String devMessage, String displayMessage) {
        this.devMessage = devMessage;
        this.displayMessage = displayMessage;
    };

    abstract public CommonResponseBody<Void> toCommonResponseBody();
}
