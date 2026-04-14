package io.github.vincentwuliango.taskmanagerapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    
    private final TaskRepository taskRepository;
    
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    @Override
    public List<Task> getTaskByFilter(String status, String category) {
        return taskRepository.findByFilter(status, category);
    }
    
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existing = getTaskById(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setCategory(updatedTask.getCategory());
        return taskRepository.save(existing);
    }
    
    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }
}
