package com.taskmanager.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.taskmanager.backend.entity.Task;
import com.taskmanager.backend.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    // ✅ Constructor Injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ✅ CREATE TASK
    @PostMapping
public ResponseEntity<Task> createTask(@RequestBody Task task) {
    Task savedTask = taskService.saveTask(task);
    return ResponseEntity.ok(savedTask);
}

    // ✅ GET ALL TASKS
    @GetMapping
public ResponseEntity<List<Task>> getTasks(
        @RequestParam(required = false) String status,
        @RequestParam(required = false) Long userId) {

    List<Task> tasks;

    if (status != null) {
        tasks = taskService.getByStatus(status);
    } else if (userId != null) {
        tasks = taskService.getByUser(userId);
    } else {
        tasks = taskService.getAllTasks();
    }

    return ResponseEntity.ok(tasks);
}

    // ✅ DELETE TASK
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    // ✅ UPDATE TASK (PARTIAL UPDATE SUPPORTED)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }
}