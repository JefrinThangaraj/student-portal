package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.User;
import com.ToDo.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username) != null;
    }
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
