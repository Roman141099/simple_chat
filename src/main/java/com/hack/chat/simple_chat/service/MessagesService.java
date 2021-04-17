package com.hack.chat.simple_chat.service;

import com.hack.chat.simple_chat.exception.PageableException;
import com.hack.chat.simple_chat.model.DataSource;
import com.hack.chat.simple_chat.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessagesService {

    private final DataSource dataSource;

    public MessagesService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveMessage(long roomKey, Message message){
        try {
            dataSource.putMsg(roomKey, message);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public List<Message> findAllByRoom(long roomId){
        return dataSource.getRoomById(roomId).getMessages();
    }

    public List<Message> findAllByRoom(long roomId, int startInclusive, int endExclusive){
        try {
            return dataSource.findPaginated(roomId, startInclusive, endExclusive);
        }catch (RuntimeException ex){
            throw new PageableException(ex.getMessage());
        }
    }

}
