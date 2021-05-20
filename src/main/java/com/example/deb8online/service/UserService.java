package com.example.deb8online.service;

import com.example.deb8online.entity.User;
import com.example.deb8online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        //Sätter default profilbild
        user.setImg("https://process.filepicker.io/APHE465sSbqvbOIStdwTyz/rotate=deg:exif/resize=fit:crop,height:566,width:944/output=quality:80,compress:true,strip:true,format:jpg/cache=expiry:max/https://cdn.filestackcontent.com/CfgaGL9xQ826bsO1rWq0");

        userRepository.save(user);
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public User getUserByUserName(String userName){

        return userRepository.findByUserName(userName);
    }

    public User getUserById(Long id){

        return userRepository.findById(id).orElseThrow();
    }

    public long getUserCount(){

        return userRepository.count();
    }

    public boolean authUser(User userLoginAttempt){

        User DBuser = userRepository.findByUserName(userLoginAttempt.getUserName());

        return DBuser!= null && DBuser.getPassword().equals(userLoginAttempt.getPassword());
    }

    public void updateUser(User user){
        System.out.println("--->" + user.getUserName());
        //Om inget nytt lösenord angavs så ska nuvarande lösenord behållas.
        if(user.getPassword().equals("")){
            user.setPassword(userRepository.findByUserName(user.getUserName()).getPassword());
        }

        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
