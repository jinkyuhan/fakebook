package com.jkhan.fakebookserver.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;
    private final String principal;

    // TODO: TODO: Authority 내용 정해지고 수정, JWT까서 나오는 권한 JwtAuthenticationToken 에 포함 시키기:: 클래스 변경
    public JwtAuthenticationToken(String bearerToken) {
        super(null);
        this.credentials = bearerToken;
        this.principal = null;
    }

    public JwtAuthenticationToken(String bearerToken, String userId) {
        super(null);
        this.credentials = bearerToken;
        this.principal = userId;
        this.setAuthenticated(true);
    }


    @Override
    public String getCredentials() {
        return this.credentials;
    }

    @Override
    public String getPrincipal() {
        return this.principal;
    }

}
