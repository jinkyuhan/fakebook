package com.jkhan.fakebookserver.auth;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, RuntimeException {
        System.out.println("asdf");
        try {
            Claims payload = jwtProvider.parseAuthHeader(request.getHeader("Authorization"));
        } catch(Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }



//        UserDetails userDetail =
        filterChain.doFilter(request, response);
    }


//    private String parseTokenFromHeader(HttpServletRequest request) throws RuntimeException {
//        String authHeader = request.getHeader("Authorization");
//        String tokenPrefix = "Bearer ";
//        if (authHeader == null || !authHeader.startsWith(tokenPrefix)) {
//            throw new RuntimeException("Invalid request authorization header");
//        }
//        return authHeader.substring(tokenPrefix.length());
//    }
}
