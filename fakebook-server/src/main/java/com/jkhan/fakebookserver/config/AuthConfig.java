package com.jkhan.fakebookserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="service.auth")
@Getter
@Setter
public class AuthConfig {
    private String jwtSecret;
    private int jwtExpireMinutes;
    private String passwordEncodingSecret; 
}
