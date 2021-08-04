package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.constant.ApiResult;

public class InvalidInputException extends RequestFailException {
    public InvalidInputException(String devMessage, String displayMessage) {
        super(devMessage, displayMessage);
    }

    @Override
    public CommonResponseBody<Void> toCommonResponseBody() {
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.INVALID_INPUT)
                .devMessage(this.getDevMessage())
                .displayMessage(this.getDisplayMessage())
                .build();
    }
}
