package com.intership.app_logist.service;

import com.intership.app_logist.dto.TaskDto;
import com.intership.app_logist.entities.Task;
import com.intership.app_logist.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public ResponseEntity<Task> create(TaskDto taskDto, String companyName) {
        Task task = new Task();
        task.setStartLocation(taskDto.getStartLocation());
        task.setEndLocation(taskDto.getEndLocation());
        task.setDriverFirstName(taskDto.getDriverFirstName());
        task.setDriverLastName(taskDto.getDriverLastName());
        task.setCargoDescription(taskDto.getCargoDescription());
        task.setVehicleNumber(taskDto.getVehicleNumber());
        task.setCompanyName(companyName);
        taskRepository.save(task);
        return ResponseEntity.status(201).body(task);
    }

    public ResponseEntity<Task> findById(UUID taskId, String companyName) {
        return taskRepository.findByIdAndCompanyName(taskId, companyName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> removeById(UUID taskId, String companyName) {
        if (taskRepository.existsByIdAndCompanyName(taskId, companyName)) {
            taskRepository.deleteById(taskId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<List<Task>> findAllByCompanyName(String companyName) {
        List<Task> tasks = taskRepository.findAllByCompanyName(companyName);
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<List<Task>> getTaskByDriver(UUID driverId, String companyName) {
        List<Task> tasks = taskRepository.findAllByDriverIdAndCompanyName(driverId, companyName);
        return ResponseEntity.ok(tasks);
    }
}