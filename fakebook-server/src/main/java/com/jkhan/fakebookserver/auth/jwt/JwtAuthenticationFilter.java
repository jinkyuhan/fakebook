package com.jkhan.fakebookserver.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String jwt = extractBearerToken(request);
            JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);
            Authentication authenticationResult = jwtProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            filterChain.doFilter(request, response);
        } catch(AuthenticationException e) {
            // if jwt token is invalid, do the remaining FilterChain without Authentication
            // TODO: 로깅 전략 구성
            System.err.println(e.getMessage());
            SecurityContextHolder.clearContext();
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return super.shouldNotFilter(request);
//    }

    private String extractBearerToken(HttpServletRequest request) throws AuthenticationException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new AuthenticationServiceException("Request doesn't have authorization header");
        }
        return authorizationHeader;
    }
}

