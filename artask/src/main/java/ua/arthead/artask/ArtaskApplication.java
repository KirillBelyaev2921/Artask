package ua.arthead.artask;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.data.repository.TaskRepository;

@SpringBootApplication
public class ArtaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TaskRepository repository) {
		return args -> {
			Task home = new Task("Home");
			Task task1 = new Task("Task 1", home);
			Task task2 = new Task("Task 2", home);
			Task task3 = new Task("Task 3", task1);
			Task task4 = new Task("Task 4", task1);
			repository.saveAll(List.of(home, task1, task2, task3, task4));
		};
	}
}
