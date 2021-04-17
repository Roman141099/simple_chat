package com.hack.chat.simple_chat.controller;

import com.hack.chat.simple_chat.model.Message;
import com.hack.chat.simple_chat.service.MessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/messages/rooms")
public class MessageController {

    private final MessagesService messagesService;

    public MessageController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{roomId}", produces = {"application/json"})
    public Set<Message> findAll(@PathVariable short roomId){
        return messagesService.findAllByRoom(roomId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{roomId}", produces = {"application/json"})
    public Set<Message> send(@PathVariable short roomId, @RequestBody Message message){
        messagesService.sendMessage(roomId, message);
        return messagesService.findAllByRoom(roomId);
    }


}
