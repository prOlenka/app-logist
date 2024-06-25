package com.intership.app_logist.controller;

import com.intership.app_logist.dto.TaskDto;
import com.intership.app_logist.entities.Task;
import com.intership.app_logist.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logist/v1/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam String companyName,
                                           @Valid @RequestBody TaskDto task) {
        return taskService.create(task, companyName);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@RequestParam String companyName,
                                        @PathVariable UUID taskId) {
        return taskService.findById(taskId, companyName);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> removeTask(@RequestParam String companyName,
                                           @PathVariable UUID taskId) {
        return taskService.removeById(taskId, companyName);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> getAllByCompany(@RequestParam String companyName) {
        return taskService.findAllByCompanyName(companyName);
    }

    @GetMapping("/byDriver/{driverId}")
    public ResponseEntity<List<Task>> getAllByDriver(@PathVariable UUID driverId,
                                                     @RequestParam String companyName) {
        return taskService.getTaskByDriver(driverId, companyName);
    }
}