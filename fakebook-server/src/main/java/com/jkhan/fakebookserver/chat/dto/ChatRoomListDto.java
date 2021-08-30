package com.jkhan.fakebookserver.chat.dto;

import com.jkhan.fakebookserver.chat.entity.ChatRoom;
import lombok.Setter;

import java.util.List;

@Setter
public class ChatRoomListDto {
    private List<ChatRoom> chatrooms;
}
