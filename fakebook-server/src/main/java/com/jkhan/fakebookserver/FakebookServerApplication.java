package com.jkhan.fakebookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FakebookServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(FakebookServerApplication.class, args);
  }

}
