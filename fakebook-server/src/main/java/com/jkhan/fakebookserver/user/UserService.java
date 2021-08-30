package com.jkhan.fakebookserver.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import com.jkhan.fakebookserver.user.dto.UserCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserAccountRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Boolean> checkIfUserAccountAlreadyExists(Supplier<Optional<UserAccount>> userAccountSupplier) {
        Map<String, Boolean> checkResult = new HashMap<>();
        checkResult.put("isDuplicated", userAccountSupplier.get().isPresent());
        return checkResult;
    }

    public Optional<UserAccount> getUserById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public Optional<UserAccount> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<UserAccount> getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public Optional<UserAccount> getUserByNicknameOrEmail(String nickname, String email) {
        return userRepository.findByNicknameOrEmail(nickname, email);
    }

    public void createNewUserAccount(UserCreationDto newUserInfo) {
        userRepository.save(UserAccount.builder()
                .nickname(newUserInfo.getNickname())
                .name(newUserInfo.getName())
                .password(passwordEncoder.encode(newUserInfo.getPassword()))
                .email(newUserInfo.getEmail())
                .age(newUserInfo.getAge())
                .build()
        );
    }
}
