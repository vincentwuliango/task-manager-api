package io.github.vincentwuliango.taskmanagerapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tasks")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    // CREATE - 201 Created
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // UPDATE - 200 Ok
    @PutMapping(path = {"/{taskId}"})
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId,
                                           @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(taskId, task));
    }
    
    // GET ALL - 200 Ok
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    // GET BY ID - 200 Ok
    @GetMapping(path = "/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }
    
    // FILTER - 200 Ok
    @GetMapping(path = "/filter")
    public ResponseEntity<List<Task>> getTaskByFilter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(taskService.getTaskByFilter(status, category));
    }
    
    
    // DELETE - 204 No content
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
