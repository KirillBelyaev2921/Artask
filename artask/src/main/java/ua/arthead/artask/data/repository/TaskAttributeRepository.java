package ua.arthead.artask.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.arthead.artask.data.model.TaskAttribute;

public interface TaskAttributeRepository extends JpaRepository<TaskAttribute<?>, Long> {

}