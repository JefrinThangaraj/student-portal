package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, String> {
}
