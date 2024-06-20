package com.intership.app_logist.controller;

import com.intership.app_logist.entities.Task;
import com.intership.app_logist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public Flux<Task> getAllTasks(Principal principal) {
        UUID companyId = getCompanyId(principal);
        return taskService.getTasksByCompanyId(companyId);
    }

    @PostMapping
    public Mono<Task> createTask(@RequestBody Task task, Principal principal) {
        UUID companyId = getCompanyId(principal);
        task.setId(companyId);
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Mono<Task> getTaskById(@PathVariable UUID id, Principal principal) {
        UUID companyId = getCompanyId(principal);
        return taskService.getTaskById(id, companyId);
    }

    private UUID getCompanyId(Principal principal) {
        // Ваша логика для получения companyId из Principal
        // Например, если companyId находится в полях пользователя
        return UUID.fromString(principal.getName());
    }
}