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

        userRepository.save(user);
    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public User getUserByName(String name){

        return userRepository.findByName(name);
    }

    public User getUserById(Long id){

        return userRepository.findById(id).orElseThrow();
    }
}
