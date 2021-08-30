package com.jkhan.fakebookserver.chat;

import com.jkhan.fakebookserver.chat.entity.ChatRoomUser;
import com.jkhan.fakebookserver.chat.entity.ChatRoomUserId;
import com.jkhan.fakebookserver.common.PageCursorVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, ChatRoomUserId> {

    public List<ChatRoomUser> findByUserId(UUID userId);

    public List<ChatRoomUser> findByUserId(UUID userId, Pageable pageable);

    @Query("SELECT cr " +
            "FROM ChatRoom cr " +
            "WHERE ((cr.createdAt = :#{#cursor.dateCursor} AND cr.id < :#{#cursor.idCursor}) " +
                "OR (cr.createdAt < :#{#cursor.dateCursor})) " +
                "AND cr.id = :userId " +
            "ORDER BY cr.createdAt DESC, cr.id DESC ")
    public List<ChatRoomUser> findByUserIdWithCursor(
            @Param("userId") UUID userId,
            @Param("cursor") PageCursorVo cursor,
            Pageable pageable
    );
}
