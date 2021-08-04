package com.jkhan.fakebookserver.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInDto {
    private String email;
    private String password;
}
