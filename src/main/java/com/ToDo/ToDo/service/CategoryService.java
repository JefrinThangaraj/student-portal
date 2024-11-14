package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.Category;
import com.ToDo.ToDo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Optional<Category> updateCategoryById(String id, Category categoryDetails) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category categoryToUpdate = existingCategory.get();
            if (categoryDetails.getTitle() != null) {
                categoryToUpdate.setTitle(categoryDetails.getTitle());
                categoryToUpdate.setPostedBy(categoryDetails.getPostedBy());
            }
            return Optional.of(categoryRepository.save(categoryToUpdate));
        } else {
            return Optional.empty();
        }
    }
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }
}
