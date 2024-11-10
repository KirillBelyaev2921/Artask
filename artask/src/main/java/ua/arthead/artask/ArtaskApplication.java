package ua.arthead.artask;

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
		return args -> repository.save(new Task("Home"));
	}
}
