package com.taskmanager.backend.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.taskmanager.backend.entity.Task;
import com.taskmanager.backend.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // ✅ Constructor Injection
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // ✅ GET ALL TASKS
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // ✅ CREATE TASK
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // ✅ DELETE TASK
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getByStatus(String status) {
    return taskRepository.findByStatus(status);
}

public List<Task> getByUser(Long userId) {
    return taskRepository.findByAssignedToId(userId);
}

    // ✅ UPDATE TASK (VERY IMPORTANT)
    public Task updateTask(Long id, Task task) {
        Task existing = taskRepository.findById(id).orElseThrow();

        if (task.getTitle() != null) {
            existing.setTitle(task.getTitle());
        }

        if (task.getDescription() != null) {
            existing.setDescription(task.getDescription());
        }

        if (task.getStatus() != null) {
            existing.setStatus(task.getStatus());
        }

        return taskRepository.save(existing);
    }
}