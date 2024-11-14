package com.ToDo.ToDo.service;

import com.ToDo.ToDo.model.Category;
import com.ToDo.ToDo.model.Task;
import com.ToDo.ToDo.repository.CategoryRepository;
import com.ToDo.ToDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Task createTask(Task task) {
        Optional<Category> category = categoryRepository.findById(task.getCategoryId());

        if (!category.isPresent()) {
            throw new RuntimeException("Category not found");
        }
        task.setCategory(category.get());

        return taskRepository.save(task);
    }
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Task> updateTaskById(String id, Task taskDetails) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            taskToUpdate.setTitle(taskDetails.getTitle());
            taskToUpdate.setPriority(taskDetails.getPriority());
            taskToUpdate.setDeadlineDate(taskDetails.getDeadlineDate());
            taskToUpdate.setDeadlineTime(taskDetails.getDeadlineTime());
            taskToUpdate.setPostedBy(taskDetails.getPostedBy());
            // Save updated task
            return Optional.of(taskRepository.save(taskToUpdate));
        } else {
            return Optional.empty();
        }
    }
    public void deleteUser(String id) {
        Task user = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        taskRepository.delete(user);
    }
}
