package com.jkhan.fakebookserver.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, ChatRoomUserId> {

    public List<ChatRoomUser> findByUserId(UUID userId);
}
