package com.jkhan.fakebookserver.chat;

import com.jkhan.fakebookserver.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
}
