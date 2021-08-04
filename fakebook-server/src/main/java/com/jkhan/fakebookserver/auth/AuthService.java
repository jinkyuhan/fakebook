package com.jkhan.fakebookserver.auth;

import java.time.Duration;
import java.util.Date;

import com.jkhan.fakebookserver.config.AuthConfig;
import com.jkhan.fakebookserver.user.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthService {
    @Autowired
    private AuthConfig jwtConfig;

    public AuthTokenDto createToken(UserAccount user) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + Duration.ofMinutes(jwtConfig.getJwtExpireMinutes()).toMillis());
        return new AuthTokenDto(
                Jwts.builder()
                        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                        .setIssuedAt(now)
                        .setExpiration(expiredAt)
                        .claim("userId", user.getId()).claim("email", user.getEmail())
                        .claim("nickname", user.getNickname())
                        .claim("name", user.getName()).signWith(SignatureAlgorithm.HS256, jwtConfig.getJwtSecret())
                        .compact(),
                expiredAt.getTime());
    }

    public Claims parseToken(String authorizationHeader) {
        String parsingTargetToken = "" + authorizationHeader;
        if (authorizationHeader.startsWith("Bearer ")) {
            parsingTargetToken = authorizationHeader.substring("Bearer ".length());
        }
        return Jwts.parser()
                .setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(parsingTargetToken)
                .getBody();
    }
}
