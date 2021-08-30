package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.constant.ApiResult;

public class DuplicationException extends RequestFailException {
    public DuplicationException(String devMessage, String displayMessage) {
        super(devMessage, displayMessage);
    }

    @Override
    public ApiResult getApiResult() {
        return ApiResult.RESOURCE_DUPLICATE;
    }
}
