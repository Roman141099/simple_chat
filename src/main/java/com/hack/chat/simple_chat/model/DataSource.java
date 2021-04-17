package com.hack.chat.simple_chat.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataSource {

    private Map<Short, Room> rooms = new HashMap<>();

    public void putMsg(short roomKey, Message message) {
        Room cur = rooms.get(roomKey);
        if(cur == null){
            cur = new Room();
            rooms.put(roomKey, cur);
        }
        cur.getMessages().add(message);
    }

    public void createRoom(short roomId){
        Room room = new Room(roomId);
        rooms.put(roomId, room);
    }

    public Room getRoomById(short roomId){
        return rooms.getOrDefault(roomId, new Room());
    }
}
