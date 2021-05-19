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
        user.setImg("https://i.pinimg.com/564x/d9/56/9b/d9569bbed4393e2ceb1af7ba64fdf86a.jpg");

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
