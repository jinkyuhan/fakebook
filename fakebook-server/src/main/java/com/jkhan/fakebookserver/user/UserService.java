package com.jkhan.fakebookserver.user;

import com.jkhan.fakebookserver.common.exception.DatabaseProcessFailException;
import com.jkhan.fakebookserver.dto.UserCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserAccountRepository userRepository;

    public Optional<UserAccount> getUserByNicknameOrEmail(String nickname, String email) {
        return userRepository.findByNicknameOrEmail(nickname, email);
    }

    public void createNewUserAccount(UserCreationDto newUserInfo) {
        try {
            userRepository.save(UserAccount.builder()
                    .nickname(newUserInfo.getNickname())
                    .name(newUserInfo.getName())
                    .password(newUserInfo.getPassword())
                    .email(newUserInfo.getEmail())
                    .age(newUserInfo.getAge())
                    .build()
            );
        } catch (Exception e) {
            throw new DatabaseProcessFailException(e.getMessage());
        }
    }
}
