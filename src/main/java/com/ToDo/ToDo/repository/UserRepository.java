package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}

