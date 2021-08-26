package com.jkhan.fakebookserver.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String name;
    private String content;
    private String sendDate;
}
