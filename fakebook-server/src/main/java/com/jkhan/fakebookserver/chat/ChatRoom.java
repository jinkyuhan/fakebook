package com.jkhan.fakebookserver.chat;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Table(name = "chat_room")
@Entity
@Getter
@Setter
public class ChatRoom {

    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public ChatRoom() {
        this.id = UUID.randomUUID();
    }
    public ChatRoom(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
