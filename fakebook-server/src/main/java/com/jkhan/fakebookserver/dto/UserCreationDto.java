package com.jkhan.fakebookserver.dto;

import lombok.*;

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
