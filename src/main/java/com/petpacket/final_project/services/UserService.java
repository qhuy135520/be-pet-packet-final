package com.petpacket.final_project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public List<User> getAllUser(){
    	return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
