package com.example.deb8online.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    private String text;

    private long messageId;

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

    public String getDateTime() {
        // Tar bort det 'T' som uppstår mellan datum och tiden innan return
        String dateTime = this.dateTime.toString();
        dateTime = dateTime.replace('T', ' ');

        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {

        this.dateTime = dateTime;
    }

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

    public String getMessageUserName() {
        return messageUserName;
    }

    public void setMessageUserName(String messageUserName) {
        this.messageUserName = messageUserName;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
