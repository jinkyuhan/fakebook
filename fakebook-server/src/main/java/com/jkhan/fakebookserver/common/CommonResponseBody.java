package com.jkhan.fakebookserver.common;

import com.jkhan.fakebookserver.constant.ApiResult;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CommonResponseBody<T> {

  private String result;
  @Builder.Default
  private String devMessage = "";
  @Builder.Default
  private String displayMessage = "";
  private T data;

  public static class CommonResponseBodyBuilder<T> {
    private String result;
    public CommonResponseBodyBuilder<T> result(ApiResult result) {
      this.result = result.getResult();
      return this;
    }
  }
}
