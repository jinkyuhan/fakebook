package com.jkhan.fakebookserver.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="service.auth")
@Getter
@Setter
public class AuthConfigConstant {
    private String jwtSecret;
    private int accessExpireMinutes;
    private int refreshExpireDays;
    private String passwordEncodingSecret;
}
