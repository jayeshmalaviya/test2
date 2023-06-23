package com.example.vacanzy.Controller;

import com.example.vacanzy.Exception.ResourceNotFoundException;
import com.example.vacanzy.Model.Task;
import com.example.vacanzy.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/get")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Task getTaskById(@PathVariable Long id) throws ResourceNotFoundException {
//        try {
//            return taskRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//        } catch (ResourceNotFoundException e) {
//            throw e;
//        }
//    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) throws ResourceNotFoundException {
        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());

            return taskRepository.save(task);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

            taskRepository.delete(task);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
}
