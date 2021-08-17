package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.CommonResponseBody;

import com.jkhan.fakebookserver.constant.ApiResult;
import lombok.Getter;

@Getter
public abstract class RequestFailException extends RuntimeException {
    private String devMessage;
    private String displayMessage;


    public RequestFailException(String devMessage, String displayMessage) {
        this.devMessage = devMessage;
        this.displayMessage = displayMessage;
    };

    public CommonResponseBody<Void> toCommonResponseBody() {
        return CommonResponseBody.<Void>builder()
                .result(this.getApiResult())
                .devMessage(this.getDevMessage())
                .displayMessage(this.getDisplayMessage())
                .build();
    }

    abstract public ApiResult getApiResult();
}
