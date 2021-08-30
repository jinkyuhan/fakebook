package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.constant.ApiResult;

public class InvalidInputException extends RequestFailException {
    public InvalidInputException(String devMessage, String displayMessage) {
        super(devMessage, displayMessage);
    }

    @Override
    public ApiResult getApiResult() {
        return ApiResult.INVALID_INPUT;
    }
}
