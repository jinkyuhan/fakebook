package com.jkhan.fakebookserver.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LoginSessionRepository extends JpaRepository<LoginSession, UUID> {
    public void deleteByUserAccountId(UUID userId);
}
