package com.jkhan.fakebookserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="service.jwt")
@Getter
@Setter
public class JwtConfig {
    private String secret;
    private int expireMinutes;
}
