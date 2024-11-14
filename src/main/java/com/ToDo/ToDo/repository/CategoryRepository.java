package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, String>{
}
