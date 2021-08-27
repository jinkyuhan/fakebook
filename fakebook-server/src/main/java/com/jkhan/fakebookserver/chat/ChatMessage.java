package com.jkhan.fakebookserver.chat;

import com.jkhan.fakebookserver.user.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "chat_message")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    @Id
    private String id;

    @Column
    private String content;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserAccount sender;

    public ChatMessage(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        this.id = chatRoom.getId().toString()
                + "_"
                + this.sendDate.format(
                        DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
