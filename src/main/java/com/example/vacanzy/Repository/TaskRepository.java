package com.example.vacanzy.Repository;

import com.example.vacanzy.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}