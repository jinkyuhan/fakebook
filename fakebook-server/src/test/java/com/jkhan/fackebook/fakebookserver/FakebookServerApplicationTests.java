package com.jkhan.fackebook.fakebookserver;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.config.AuthConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FakebookServerApplication.class)
public class FakebookServerApplicationTests {

  @Autowired
  private AuthConfig authConfig;

  @Test
  void contextLoads() {
  }

}
