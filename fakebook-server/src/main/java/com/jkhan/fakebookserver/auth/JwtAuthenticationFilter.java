package com.jkhan.fakebookserver.auth;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 예상: Security Config 에 addFilterBefore 안해주면 이 필터를 통과하지 않음)
    // 현재: Security Config 와 상관없이 OncePerRequestFilter 만 extends 하는 것만으로 filter가 작동함)
    // 뭐가 문제일까...
    // 또한
    // Security Config 에서 filter 가 걸리는 예외 경로를 정해줘야함 (이 클래스 안에서 string match 하는 방법도 있겠지만 지양) -> 작동안함
    // Jwt 를 도입한 이상 Spring security 의 UsernameAndPasswordAuthenticationFilter 가 더 이상 필요 없는데 끄는 방법 모르겟음
    // Security Config 에서 entryPoint 로 하는 exception handling 과 아래에서 한 exception handling 의 차이

    @Autowired
    private JwtProvider jwtProvider;

    private final Map<String, String[]> exclusionUrlsList;

    public JwtAuthenticationFilter() {
        super();
        this.exclusionUrlsList = new HashMap<>();
        this.exclusionUrlsList.put(HttpMethod.GET.toString(), new String[]{
                "/api/user/mail/duplicate",
                "/api/user/nickname/duplicate"
        });
        this.exclusionUrlsList.put(HttpMethod.POST.toString(), new String[]{
                "/api/auth/login",
                "/api/user"
        });
//        this.exclusionUrlsList.put(HttpMethod.PATCH.toString(), new String[]{});
//        this.exclusionUrlsList.put(HttpMethod.DELETE.toString(), new String[]{});
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, RuntimeException {
        try {
            // if jwt token is valid, do the remaining FilterChain with an Authentication.
            Authentication authentication = jwtProvider.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            // if jwt token is invalid, do the remaining FilterChain with empty Authentication.
            // The empty Authentication is can't pass the UsernamePasswordAuthenticationFilter
            System.err.println(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludedPatterns = this.exclusionUrlsList.get(request.getMethod());
        if (excludedPatterns == null) {
            throw new RuntimeException("not supplied http method");
        }
        return Arrays.stream(excludedPatterns).anyMatch((url) -> url.equals(request.getRequestURI()));
    }
}

