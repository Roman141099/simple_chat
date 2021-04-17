package com.hack.chat.simple_chat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Room {

    private long roomId;

    private List<Message> messages = new ArrayList<>();

    public Room() {
    }

    public Room(long id) {
        this.roomId = id;
    }
}
