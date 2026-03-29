package com.taskmanager.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.taskmanager.backend.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(String status);

    List<Task> findByAssignedToId(Long id);
}