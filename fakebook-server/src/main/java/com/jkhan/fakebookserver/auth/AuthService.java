package com.jkhan.fakebookserver.auth;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jkhan.fakebookserver.config.AuthConfig;
import com.jkhan.fakebookserver.user.UserAccount;

import com.jkhan.fakebookserver.user.UserLoginSession;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    private AuthConfig authConfig;

    public AuthTokenBundleDto issueNewLoginSession(UserAccount user) {
        AuthTokenBundleDto newTokenBundle = new AuthTokenBundleDto();
        UserLoginSession newLoginSession = new UserLoginSession(user);
        newTokenBundle.setAccessToken(generateAccessToken(user));
        newTokenBundle.setRefreshToken(generateRefreshToken(user, newLoginSession));
        // TODO: save newLoginSession into DB with Transaction ( Consider to save into redis as sessions store )
        return newTokenBundle;
    }

    private AuthTokenDto generateAccessToken(UserAccount user) {
        return createToken(
                Duration.ofMinutes(authConfig.getAccessExpireMinutes()).toMillis(),
                extractClaimsFromUserAccount(user));
    }

    private AuthTokenDto generateRefreshToken(UserAccount user, UserLoginSession loginSession) {
        Map<String, Object> payload = extractClaimsFromUserAccount(user);
        payload.put("loginSessionId", loginSession.getId());
        return createToken(Duration.ofDays(authConfig.getRefreshExpireDays()).toMillis(), payload);
    }


    private Map<String, Object> extractClaimsFromUserAccount(UserAccount user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        return claims;
    }

    private AuthTokenDto createToken(long remainingExpirationMillis, Map<String, Object> payload) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + remainingExpirationMillis);
        return new AuthTokenDto(
                Jwts.builder()
                        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                        .setIssuedAt(now)
                        .setExpiration(expiredAt)
                        .setClaims(payload)
                        .signWith(SignatureAlgorithm.HS256, authConfig.getJwtSecret())
                        .compact(),
                expiredAt.getTime());
    }

    public Claims parseAuthHeader(String authorizationHeader) {
        String parsingTargetToken = "" + authorizationHeader;
        if (authorizationHeader.startsWith("Bearer ")) {
            parsingTargetToken = authorizationHeader.substring("Bearer ".length());
        }
        return Jwts.parser()
                .setSigningKey(authConfig.getJwtSecret())
                .parseClaimsJws(parsingTargetToken)
                .getBody();
    }

}
