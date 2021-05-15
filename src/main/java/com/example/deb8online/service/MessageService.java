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

        //Hämtar ID efter att det har autogenererats i databsen och tilldelar messageID
        //Detta var för att formuläret för delete i message_board.html behöver
        //olika namn på id för User och id för Message.
        message.setMessageId(message.getId());

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

    public boolean deleteMessage(Message message, User user){

        if(message.getMessageUserId() == user.getId()){

            //Fix för att thymeleaf formuläret i msg_board.html ger message.id samma värde som
            // user.id av någon anledning. Här sätts rätt message.id igen för
            // att delete ska fungera med rätt id i databasen.
            message.setId(message.getMessageId());

            messageRepository.delete(message);
            System.out.println("-> message id = " + message.getMessageId());
            return true;
        }
        else{
            return false;
        }
    }
}
