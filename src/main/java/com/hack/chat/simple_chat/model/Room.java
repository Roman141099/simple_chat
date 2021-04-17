package com.hack.chat.simple_chat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
//@Entity
//@Table(name = "rooms")
public class Room {

    @Id
    private short roomId;

//    @OneToMany
    private Set<Message> messages= new TreeSet<>(Comparator.comparing(Message::getSendTime));

    public Room() {
    }

    public Room(short id) {
        this.roomId = id;
    }

    public short getId() {
        return roomId;
    }

    public void setId(short id) {
        this.roomId = id;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
