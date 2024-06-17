package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
