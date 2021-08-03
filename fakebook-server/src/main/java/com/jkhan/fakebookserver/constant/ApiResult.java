package com.jkhan.fakebookserver.constant;

import lombok.Getter;

public enum ApiResult {
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    @Getter
    private String result;

    ApiResult(String resultCode) {
        this.result = resultCode;
    }
}