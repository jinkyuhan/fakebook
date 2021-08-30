package com.jkhan.fakebookserver.chat.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jkhan.fakebookserver.chat.entity.ChatRoom;
import com.jkhan.fakebookserver.user.UserAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // MessageType enum 추가
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserAccount sender;

    public ChatMessage(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        this.id = chatRoom.getId().toString()
                + "_"
                + formatter.format(this.sendDate);
    }
}
