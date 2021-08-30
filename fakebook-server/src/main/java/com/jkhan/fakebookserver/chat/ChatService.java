package com.jkhan.fakebookserver.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    public List<ChatRoom> getAllChatRoomsOfUser(UUID userId) {
        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findByUserId(userId);
        return chatRoomUsers.stream()
                .map(ChatRoomUser::getChatRoom)
                .collect(Collectors.toList());
    }
}
