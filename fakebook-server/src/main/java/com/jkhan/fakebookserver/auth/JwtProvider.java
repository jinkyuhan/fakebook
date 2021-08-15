package com.jkhan.fakebookserver.auth;

import java.time.Duration;
import java.util.*;

import com.jkhan.fakebookserver.config.AuthConfig;
import com.jkhan.fakebookserver.user.UserAccount;

import com.jkhan.fakebookserver.user.UserAccountRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtProvider {

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    public AuthTokenBundleDto issueNewLoginSession(UserAccount user) {
        AuthTokenBundleDto newTokenBundle = new AuthTokenBundleDto();
        newTokenBundle.setAccessToken(generateAccessToken(user));

        LoginSession newLoginSession = new LoginSession(user);
        AuthTokenDto newRefreshToken = generateRefreshToken(user, newLoginSession);
        newTokenBundle.setRefreshToken(newRefreshToken);

        newLoginSession.setExpiredAt(newRefreshToken.getExpiredAt());
        loginSessionRepository.save(newLoginSession);

        return newTokenBundle;
    }

    private AuthTokenDto generateAccessToken(UserAccount user) {
        return createToken(
                Duration.ofMinutes(authConfig.getAccessExpireMinutes()).toMillis(),
                user.getJwtClaims());
    }

    private AuthTokenDto generateRefreshToken(UserAccount user, LoginSession loginSession) {
        Map<String, Object> payload = user.getJwtClaims();
        payload.put("loginSessionId", loginSession.getId());
        return createToken(Duration.ofDays(authConfig.getRefreshExpireDays()).toMillis(), payload);
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

    public Authentication authenticate(HttpServletRequest request) {
        Claims payload = parseAuthHeader(request.getHeader("Authorization"));
        String userId = (String) payload.get("userId");
        String maskedPassword = "";
        // TODO: Authority 내용 정해지고 구현 이후에 DB 바탕으로 정해지도록 수정
        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails principal = new User(userId, "", roles);
//        UserAccount principal = userAccountRepository.findById(UUID.fromString(userId))
//                .orElseThrow(() -> new RuntimeException("Invalid token payload")) ;
        return new UsernamePasswordAuthenticationToken(principal,"");
    }

    private Claims parseAuthHeader(String authorizationHeader) {
        String parsingTargetToken = "" + authorizationHeader;
        if (parsingTargetToken.startsWith("Bearer ")) {
            parsingTargetToken = authorizationHeader.substring("Bearer ".length());
        }
        return Jwts.parser()
                .setSigningKey(authConfig.getJwtSecret())
                .parseClaimsJws(parsingTargetToken)
                .getBody();
    }


}
