package com.jkhan.fakebookserver.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat_message")
    public void sendChatMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/sub/test", message);
    }

}