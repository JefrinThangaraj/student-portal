package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String > {
    Optional<Login> findByEmail(String email);
}