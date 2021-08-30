package com.jkhan.fakebookserver.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthTokenDto {
    private String encodedJwt;
    private long expiredAt;
}
