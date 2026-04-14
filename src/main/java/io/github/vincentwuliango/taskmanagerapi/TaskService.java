package io.github.vincentwuliango.taskmanagerapi;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> getTaskByFilter(String status, String category);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
}
