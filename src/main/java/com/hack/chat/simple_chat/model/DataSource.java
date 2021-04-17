package com.hack.chat.simple_chat.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DataSource {

    private final Map<Long, Room> rooms = new HashMap<>();
    private final AtomicInteger roomsIncrement = new AtomicInteger(1);
    private final AtomicInteger msgsIncrement = new AtomicInteger(1);

    public void putMsg(long roomKey, Message message) {
        if(message == null) {
            throw new IllegalArgumentException("Message is null");
        }
        Room cur = rooms.get(roomKey);
        if (cur == null) {
            cur = new Room(roomsIncrement.getAndIncrement());
            rooms.put(roomKey, cur);
        }
        if(message.getMessageId() == 0) {
            message.setMessageId(msgsIncrement.getAndIncrement());
        }
        cur.getMessages().add(message);
    }

    public void createRoom(long roomId) {
        Room room = new Room(roomsIncrement.getAndIncrement());
        rooms.put(roomId, room);
    }

    public Room getRoomById(long roomId) {
        return rooms.getOrDefault(roomId, new Room());
    }

    public List<Message> findPaginated(long roomId, int startInclusive, int endExclusive) {
        Optional<Room> optional = Optional.of(rooms.get(roomId));
        List<Message> messages = optional.orElseThrow(IllegalArgumentException::new).getMessages();
        if(endExclusive == - 1 || (endExclusive - startInclusive) > messages.size())
            return messages.subList(startInclusive, messages.size());
        return messages.subList(startInclusive, endExclusive);
    }
}
