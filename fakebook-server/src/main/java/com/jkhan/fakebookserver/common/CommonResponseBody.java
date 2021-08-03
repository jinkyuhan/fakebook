package com.jkhan.fakebookserver.common;

import com.jkhan.fakebookserver.constant.ApiResult;
import lombok.*;

@Builder
@Data
public class CommonResponseBody<T> {

    private String result;
    @Builder.Default
    private String devMsg = "";
    private T data;

    public static class CommonResponseBodyBuilder {
        private String result;
        public CommonResponseBodyBuilder result(ApiResult result) {
            this.result = result.getResult();
            return this;
        }
    }
}
