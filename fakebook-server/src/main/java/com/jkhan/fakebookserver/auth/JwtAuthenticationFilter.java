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

    // 예상: Security Config 에 addFilterBefore 안해주면 이 필터를 통과하지 않음)
    // 현재: Security Config 와 상관없이 OncePerRequestFilter 만 extends 하는 것만으로 filter가 작동함)
    // 뭐가 문제일까...
    // 또한
    // Security Config 에서 filter 가 걸리는 예외 경로를 정해줘야함 (이 클래스 안에서 string match 하는 방법도 있겠지만 지양)
    // Jwt 를 도입한 이상 Spring security 의 UsernameAndPasswordAuthenticationFilter 가 더 이상 필요 없는데 끄는 방법 모르겟음
    // Security Config 에서 entryPoint 로 하는 exception handling 과 아래에서 한 exception handling 의 차이
    

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
