package com.jkhan.fakebookserver.auth.config;


import com.jkhan.fakebookserver.auth.jwt.JwtAuthenticationFilter;
import com.jkhan.fakebookserver.auth.jwt.JwtProvider;
import com.jkhan.fakebookserver.auth.oauth2.CustomOAuth2UserService;
import com.jkhan.fakebookserver.auth.oauth2.OAuth2AuthenticationFailureHandler;
import com.jkhan.fakebookserver.auth.oauth2.OAuth2AuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.POST,
                        "/api/auth/login",
                        "/api/users")
                .antMatchers(
                        HttpMethod.GET,
                        "/api/users/mail/duplicate",
                        "/api/users/nickname/duplicate",
                        "/pages/**",
                        "/ws/**",
                        "/sub/**",
                        "/pub/**",
                        "/oauth2/authorization/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
        http.oauth2Login()
            .authorizationEndpoint().baseUri("/oauth2/authorize")
            .and()
            .userInfoEndpoint().userService(customOAuth2UserService)
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .failureHandler(oAuth2AuthenticationFailureHandler);
    }
}
