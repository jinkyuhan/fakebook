package com.jkhan.fakebookserver.common;

import com.jkhan.fakebookserver.common.exception.DatabaseProcessFailException;
import com.jkhan.fakebookserver.common.exception.RequestFailException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonRestControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RequestFailException.class)
    public CommonResponseBody<Void> handleRequestFailException(RequestFailException exception) {
        // TODO: 로깅 전략 구성
        System.out.println(exception.getDevMessage());
        System.out.println(exception.getMessage());
        return exception.toCommonResponseBody();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class, DatabaseProcessFailException.class})
    public CommonResponseBody<Void> handleExceptionByServer(Exception exception) {
        // TODO: 로깅 전략 구성
        System.out.println(exception.getMessage());
        return CommonResponseBody.<Void>builder()
                .devMessage("서버 문제로 인해 요청 실패")
                .displayMessage("알 수 없는 오류 입니다.")
                .build();
    }
}
