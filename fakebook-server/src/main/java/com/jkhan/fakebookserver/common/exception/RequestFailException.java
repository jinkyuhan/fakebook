package com.jkhan.fakebookserver.common.exception;

import lombok.Getter;

@Getter
public class RequestFailException extends RuntimeException {
    protected String devMessage = "";
    protected String displayMessage = "";


    public RequestFailException(String devMessage, String displayMessage) {
        super();
        this.devMessage = devMessage;
        this.displayMessage = displayMessage;
    }

    public RequestFailException(String devMessage) {
        super();
        this.devMessage = devMessage;
        this.displayMessage = "";
    }

    public RequestFailException() {
        super();
    }
}
