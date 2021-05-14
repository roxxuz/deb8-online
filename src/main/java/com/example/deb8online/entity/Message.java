package com.example.deb8online.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@Column(columnDefinition = "TIMESTAMP")
    //private LocalDateTime dateTime;

    private String text;
    private String messageUserName;
    private long messageUserId;

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //public LocalDateTime getDateTime() {
    //    return dateTime;
    //}

    //public void setDateTime(LocalDateTime dateTime) {
    //    this.dateTime = dateTime;
    //}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(long user_id) {
        this.messageUserId = user_id;
    }
}
