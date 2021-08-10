package com.jkhan.fakebookserver.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginSessionRepository extends JpaRepository<LoginSession, UUID> {
}
