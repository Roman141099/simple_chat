package com.hack.chat.simple_chat.controller;

import com.hack.chat.simple_chat.model.Message;
import com.hack.chat.simple_chat.service.MessagesService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/chats/rooms")
public class MessageController {

    private final MessagesService messagesService;

    public MessageController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    //all msgs
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{roomId}", produces = {"application/json"})
    public List<Message> findAll(@PathVariable long roomId) {
        return messagesService.findAllByRoom(roomId);
    }

    //paginated msgs
    //(end - start > size) || end == - 1 ? return start -> size else -> []
    @GetMapping(value = "/pages/{roomId}", produces = {"application/json"})
    public ResponseEntity<List<Message>> findAll(@PathVariable long roomId, @RequestParam int start,
                                                 @RequestParam(required = false) Optional<Integer> end) {
        try {
            return new ResponseEntity<>(messagesService.
                    findAllByRoom(roomId, start, end.orElse(-1)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(Collections.emptyList(), addCustomHeaders("whyBad", ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{roomId}", produces = {"application/json"})
    public ResponseEntity<List<Message>> send(@PathVariable long roomId, @RequestBody Message message) {
        try {
            messagesService.saveMessage(roomId, message);
            return new ResponseEntity<>(Collections.singletonList(message), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.emptyList(), addCustomHeaders("whyBad", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    private HttpHeaders addCustomHeaders(Map<String, String> headerTypes){
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> entry:
             headerTypes.entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        return headers;
    }

    private HttpHeaders addCustomHeaders(String key, String val){
        HttpHeaders headers = new HttpHeaders();
        headers.add(key, val);
        return headers;
    }
}
