package com.intership.app_logist.controller;

import com.intership.app_logist.entities.Task;
import com.intership.app_logist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam int page, @RequestParam int size) {
        return taskRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}