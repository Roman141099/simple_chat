package com.hack.chat.simple_chat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
public class Message {

    private int messageId;
    private String messageContent;
    private LocalDateTime sendTime;
    private long userId;
    private String userName;
    private String responseMessage;
    private String responseUser;

    public Message() {
    }

    public Message(int messageId, String messageContent, LocalDateTime sendTime, long userId, String userName, String responseMessage, String responseUser) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.userId = userId;
        this.userName = userName;
        this.responseMessage = responseMessage;
        this.responseUser = responseUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }
}
