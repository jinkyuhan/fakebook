package com.jkhan.fakebookserver.constant;

import lombok.Getter;

public enum ApiResult {
    SUCCESS("SUCCESS"),
    INVALID_INPUT("INVALID_INPUT"),
    RESOURCE_DUPLICATE("RESOURCE_DUPLICATE"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND");

    @Getter
    private String result;

    ApiResult(String resultCode) {
        this.result = resultCode;
    }
}