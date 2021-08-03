package com.jkhan.fakebookserver.common;

import com.jkhan.fakebookserver.common.exception.DatabaseProcessFailException;
import com.jkhan.fakebookserver.common.exception.DuplicationException;
import com.jkhan.fakebookserver.constant.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonRestControllerAdvice {

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<CommonResponseBody> handleDuplicationException(DuplicationException exception) {
        // TODO: 로깅 전략 구성
        System.out.println(exception.getMessage());
        return new ResponseEntity(
                CommonResponseBody
                        .builder()
                        .result(ApiResult.FAIL)
                        .devMsg("Duplication nickname or email")
                        .build(),
                HttpStatus.OK
        );
    }

    @ExceptionHandler({Exception.class, RuntimeException.class, DatabaseProcessFailException.class})
    public ResponseEntity<CommonResponseBody> handleUnknownException(Exception e) {
        // TODO: 로깅 전략 구성
        System.out.println(e.getMessage());
        return new ResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
