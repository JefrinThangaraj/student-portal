package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.Login;
import com.ToDo.ToDo.model.Role;
import com.ToDo.ToDo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    public Login createUser(Login user) {
        if (loginRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        }
        validatePassword(user.getPassword(), user.getConfirmPassword());
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return loginRepository.save(user);
    }
    public Optional<Login> getUserById(String id) {

        return loginRepository.findById(id);
    }
    public Login updateUser(String id, Login userDetails) {
        Login user = loginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        validatePassword(userDetails.getPassword(), userDetails.getConfirmPassword());
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setConfirmPassword(userDetails.getConfirmPassword());
        user.setRole(userDetails.getRole());
        return loginRepository.save(user);
    }
    public void deleteUser(String id) {
        Login user = loginRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        loginRepository.delete(user);
    }
    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }
    }
}
