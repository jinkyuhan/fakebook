package com.jkhan.fakebookserver.auth;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthTokenBundleDto {
    private AuthTokenDto accessToken;
    private AuthTokenDto refreshToken;
}
