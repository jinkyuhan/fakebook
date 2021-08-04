package com.jkhan.fakebookserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SignInDto {
    private String email;
    private String password;
}
