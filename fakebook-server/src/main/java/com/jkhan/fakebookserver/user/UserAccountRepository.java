package com.jkhan.fakebookserver.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByNicknameOrEmail(String nickname, String email);
    Optional<UserAccount> findByNickname(String nickname);
    Optional<UserAccount> findByEmail(String nickname);
}

