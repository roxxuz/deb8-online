package com.example.deb8online.service;

import com.example.deb8online.entity.Message;
import com.example.deb8online.entity.User;
import com.example.deb8online.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message){

        message.setDateTime(getCurrentDateTime());

        messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAllByOrderByIdDesc();
    }

    public LocalDateTime getCurrentDateTime() {
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return now;
    }
}
