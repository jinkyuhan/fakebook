package com.jkhan.fakebookserver.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserCreationDto {
  private String nickname;
  private String name;
  private String password;
  private String email;
  private int age;
}
