package ua.arthead.artask.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.arthead.artask.data.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}