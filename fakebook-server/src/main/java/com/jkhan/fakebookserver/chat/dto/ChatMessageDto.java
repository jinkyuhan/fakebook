package com.jkhan.fakebookserver.chat.dto;


import lombok.Getter;

@Getter
public class ChatMessageDto {

    private String content;
    private String userId;
    private String roomId;
    private String senderId;
}
