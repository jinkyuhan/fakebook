package com.jkhan.fakebookserver.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "chat_room")
@Entity
@Getter
@Setter
public class ChatRoom {

    @Id
    private UUID id;

    @Column
    private String name;

    public ChatRoom() {
        this.id = UUID.randomUUID();
    }

    public ChatRoom(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
