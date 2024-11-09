package ua.arthead.artask.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.data.repository.TaskRepository;

@Service
@AllArgsConstructor
public class TaskService {

  private final TaskRepository taskRepository;

  public List<Task> getAllTasks() {
    return taskRepository.findAll();
  }

  public Task saveTask(Task task) {
    return taskRepository.save(task);
  }

  public Optional<Task> getTaskById(long id) {
    return taskRepository.findById(id);
  }

  public void deleteTaskById(long id) {
    taskRepository.deleteById(id);
  }

}
