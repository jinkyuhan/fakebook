package com.jkhan.fakebookserver.chat;

import com.jkhan.fakebookserver.chat.entity.ChatRoom;
import com.jkhan.fakebookserver.chat.entity.ChatRoomUser;
import com.jkhan.fakebookserver.common.PageCursorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public List<ChatRoom> getFirstPageOfChatRoomsOfUser(UUID userId, int limit) {
        return chatRoomUserRepository.findByUserId(userId, PageRequest.of(0, limit)).stream()
                .map(ChatRoomUser::getChatRoom)
                .collect(Collectors.toList());
    }

    public List<ChatRoom> getChatRoomsOfUserWithCursor(UUID userId, int limit, PageCursorVo cursor) {
        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findByUserIdWithCursor(
                userId,
                cursor,
                PageRequest.of(0, limit)
        );
        return chatRoomUsers.stream().map(ChatRoomUser::getChatRoom).collect(Collectors.toList());
    }
}
