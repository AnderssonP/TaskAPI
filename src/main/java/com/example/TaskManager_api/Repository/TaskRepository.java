package com.example.TaskManager_api.Repository;

import com.example.TaskManager_api.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
