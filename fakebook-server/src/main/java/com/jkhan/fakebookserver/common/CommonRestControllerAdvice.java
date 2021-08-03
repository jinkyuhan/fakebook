package com.jkhan.fakebookserver.common;

import com.jkhan.fakebookserver.common.exception.DatabaseProcessFailException;
import com.jkhan.fakebookserver.common.exception.DuplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonRestControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<CommonResponseBody> handleDuplicationException(DuplicationException exception) {
        // TODO: 로깅 전략 구성
        System.out.println(exception.getMessage());
        return ResponseEntity.ok(
                CommonResponseBody
                        .builder()
                        .build()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class, DatabaseProcessFailException.class})
    public ResponseEntity<Void> handleUnknownException(Exception e) {
        // TODO: 로깅 전략 구성
        return ResponseEntity.internalServerError().build();
    }
}
