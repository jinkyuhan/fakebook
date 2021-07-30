package com.jkhan.fakebookserver.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping()
  public String getUser() {
    return "";
  }

}
