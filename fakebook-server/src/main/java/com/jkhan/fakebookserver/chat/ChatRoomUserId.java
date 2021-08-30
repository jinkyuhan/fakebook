package com.jkhan.fakebookserver.chat;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ChatRoomUserId implements Serializable {
    private UUID chatRoom;
    private UUID user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomUserId that = (ChatRoomUserId) o;
        return Objects.equals(user, that.user) && Objects.equals(chatRoom, that.chatRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, chatRoom);
    }
}
