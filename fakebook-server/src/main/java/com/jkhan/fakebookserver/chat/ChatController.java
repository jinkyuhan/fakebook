package com.jkhan.fakebookserver.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    @MessageMapping("/hi") // /app/hi 로 들어오는 연결을
    @SendTo("/topic/public") // 지정 토픽으로 보내기
    public ChatMessage sendMessaging() {
        System.out.println("message send");
        ChatMessage chat = new ChatMessage();
        chat.setContent("hello");
        return chat;
    }

    @MessageMapping("/chat/message")
    public void sendChatMessage() {

    }
}