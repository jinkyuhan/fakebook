package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.constant.ApiResult;

public class ResourceNotFoundException extends RequestFailException {
    public ResourceNotFoundException(String devMessage, String displayMessage) {
        super(devMessage, displayMessage);
    }

    @Override
    public ApiResult getApiResult() {
        return ApiResult.RESOURCE_NOT_FOUND;
    }
}
