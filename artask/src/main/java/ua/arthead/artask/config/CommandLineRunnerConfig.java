package ua.arthead.artask.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.data.repository.TaskRepository;

@Configuration
public class CommandLineRunnerConfig {

  @Bean
  public CommandLineRunner commandLineRunner(TaskRepository repository) {
    return args -> {
      Task home = new Task("Home");
      repository.save(home);
    };
  }

}
