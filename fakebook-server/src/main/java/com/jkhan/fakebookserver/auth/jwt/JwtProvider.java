package com.jkhan.fakebookserver.auth.jwt;

import java.time.Duration;
import java.util.*;

import com.jkhan.fakebookserver.auth.AuthConfigConstant;
import com.jkhan.fakebookserver.auth.LoginSession;
import com.jkhan.fakebookserver.auth.LoginSessionRepository;
import com.jkhan.fakebookserver.auth.dto.AuthTokenBundleDto;
import com.jkhan.fakebookserver.auth.dto.AuthTokenDto;
import com.jkhan.fakebookserver.user.UserAccount;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JwtProvider {

    @Autowired
    private AuthConfigConstant authConfigConstant;

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    @Transactional
    public AuthTokenBundleDto issueNewLoginSession(UserAccount user) {
        AuthTokenBundleDto newTokenBundle = new AuthTokenBundleDto();
        newTokenBundle.setAccessToken(generateAccessToken(user));

        LoginSession newLoginSession = new LoginSession(user);
        AuthTokenDto newRefreshToken = generateRefreshToken(user, newLoginSession);
        newTokenBundle.setRefreshToken(newRefreshToken);

        newLoginSession.setExpiredAt(new Date(newRefreshToken.getExpiredAt()));
        loginSessionRepository.save(newLoginSession);

        return newTokenBundle;
    }

    private AuthTokenDto generateAccessToken(UserAccount user) {
        return createToken(
                Duration.ofMinutes(authConfigConstant.getAccessExpireMinutes()).toMillis(),
                user.getJwtClaims());
    }

    private AuthTokenDto generateRefreshToken(UserAccount user, LoginSession loginSession) {
        Map<String, Object> payload = user.getJwtClaims();
        payload.put("loginSessionId", loginSession.getId());
        return createToken(Duration.ofDays(authConfigConstant.getRefreshExpireDays()).toMillis(), payload);
    }

    private AuthTokenDto createToken(long remainingExpirationMillis, Map<String, Object> payload) {
        Date now = new Date();
        Date expiredAt = new Date(now.getTime() + remainingExpirationMillis);
        return new AuthTokenDto(
                Jwts.builder()
                        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                        .setIssuedAt(now)
                        .setExpiration(expiredAt)
                        .addClaims(payload)
                        .signWith(SignatureAlgorithm.HS256, authConfigConstant.getJwtSecret())
                        .compact(),
                expiredAt.getTime());
    }

    public Authentication authenticate(JwtAuthenticationToken authentication) throws AuthenticationException {
        try {
            Claims payload = validateJwtToken(authentication.getCredentials());

            // TODO: Authority 내용 정해지고 수정, JWT까서 나오는 권한 JwtAuthenticationToken 에 포함 시키기.
            // Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
            // roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            Object userId = payload.get("userId");
            if (userId == null) {
                throw new MalformedJwtException("Payload include invalid claims");
            }
            JwtAuthenticationToken result = new JwtAuthenticationToken(
                    authentication.getCredentials(),
                    String.valueOf(userId)
            );

            Object loginSessionId = payload.get("loginSessionId");
            if (loginSessionId != null) {
                result.setDetails(String.valueOf(loginSessionId));
            }

            return result;
        } catch(JwtException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }


    private Claims validateJwtToken(String bearerToken) throws JwtException {
        String tokenToValidate = "" + bearerToken;
        if (!tokenToValidate.startsWith("Bearer ")) {
            throw new MalformedJwtException("Bearer token not exists authorization header");
        }
        tokenToValidate = tokenToValidate.substring("Bearer ".length());
        return Jwts.parser()
                .setSigningKey(authConfigConstant.getJwtSecret())
                .parseClaimsJws(tokenToValidate)
                .getBody();
    }

}
