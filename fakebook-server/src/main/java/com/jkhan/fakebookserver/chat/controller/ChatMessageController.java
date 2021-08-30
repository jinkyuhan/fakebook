package com.jkhan.fakebookserver.chat.controller;

import com.jkhan.fakebookserver.chat.dto.ChatMessageDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat_message")
    public void sendChatMessage(Message<ChatMessageDto> message, Authentication auth) {

        messagingTemplate.convertAndSend("/sub/chatroom/1", message);
    }

}