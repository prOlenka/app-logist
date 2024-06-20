package com.intership.app_logist.service;

import com.intership.app_logist.entities.Task;
import com.intership.app_logist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public Mono<Task> createTask(Task task) {
        return taskRepository.save(task);
    }

    public Flux<Task> getTasksByCompanyId(UUID companyId) {
        return taskRepository.findAllByCompanyId(companyId);
    }

    public Mono<Task> getTaskById(UUID id, UUID companyId) {
        // Логика получения задания по id и проверка принадлежности компании
        return taskRepository.findByIdAndCompanyId(id, companyId);
    }
}