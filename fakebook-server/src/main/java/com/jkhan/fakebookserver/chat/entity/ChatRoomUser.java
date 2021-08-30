package com.jkhan.fakebookserver.chat.entity;

import com.jkhan.fakebookserver.user.UserAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Table(name = "chatroom_user")
@Entity
@IdClass(ChatRoomUserId.class)
@Getter
@Setter
public class ChatRoomUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @Column(name = "join_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinTime;
}
