package com.jkhan.fackebook.fakebookserver;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.config.JwtConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FakebookServerApplication.class)
public class FakebookServerApplicationTests {


    @Autowired
    private JwtConfig jwtConfig;

    @Test
    void contextLoads() {
        assertNotNull(jwtConfig.getSecret());
        assertNotNull(jwtConfig.getExpireMinutes());
    }


}
