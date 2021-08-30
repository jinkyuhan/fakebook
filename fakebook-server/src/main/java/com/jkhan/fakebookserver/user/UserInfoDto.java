package com.jkhan.fakebookserver.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserInfoDto {
    private String userId;
    private String name;
    private String nickname;
    private String email;
    private long createdAt;
    private long updatedAt;
    private int age;

    public static UserInfoDto fromEntity(UserAccount user) {
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setUserId(String.valueOf(user.getId()));
        userInfo.setName(user.getName());
        userInfo.setNickname(user.getNickname());
        userInfo.setEmail(user.getEmail());
        userInfo.setCreatedAt(user.getCreatedAt().getTime());
        userInfo.setUpdatedAt(user.getUpdatedAt().getTime());
        userInfo.setAge(user.getAge());
        return userInfo;
    }

}
