package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByEmail (String email);

    boolean existsByPhoneNumber(String phoneNumber);
}