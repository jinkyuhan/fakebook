package com.jkhan.fakebookserver.chat;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
