package com.hack.chat.simple_chat.service;

import com.hack.chat.simple_chat.model.DataSource;
import com.hack.chat.simple_chat.model.Message;
import com.hack.chat.simple_chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MessagesService {

    private final DataSource dataSource;
//    private final MessageRepository messageRepository;

    public MessagesService(DataSource dataSource, MessageRepository messageRepository) {
        this.dataSource = dataSource;
//        this.messageRepository = messageRepository;
    }

    public void sendMessage(short roomKey, Message message){
        try {
            dataSource.putMsg(roomKey, message);
            message.setSent(true);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Set<Message> findAllByRoom(short roomId){
        return dataSource.getRoomById(roomId).getMessages();
    }

    public void saveMessage(short roomKey, Message message){

    }
}
