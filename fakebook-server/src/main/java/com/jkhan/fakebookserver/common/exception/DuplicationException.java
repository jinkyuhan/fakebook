package com.jkhan.fakebookserver.common.exception;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.constant.ApiResult;

public class DuplicationException extends RequestFailException {
    public DuplicationException(String devMessage, String displayMessage) {
        super(devMessage, displayMessage);
    }

    @Override
    public CommonResponseBody<Void> toCommonResponseBody() {
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.RESOURCE_DUPLICATE)
                .devMessage(this.getDevMessage())
                .displayMessage(this.getDisplayMessage())
                .build();
    }
}
