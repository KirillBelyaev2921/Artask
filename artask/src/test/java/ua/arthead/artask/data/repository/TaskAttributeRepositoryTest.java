package ua.arthead.artask.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.arthead.artask.config.CommandLineRunnerConfig;
import ua.arthead.artask.data.model.IntegerTaskAttribute;
import ua.arthead.artask.data.model.StringTaskAttribute;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.data.model.TaskAttribute;

@DataJpaTest(excludeAutoConfiguration = {CommandLineRunnerConfig.class})
class TaskAttributeRepositoryTest {

  private TaskAttributeRepository attributeRepository;
  private TaskRepository taskRepository;
  private Task homeTask;

  @Autowired
  public void setRepository(TaskAttributeRepository attributeRepository,
      TaskRepository taskRepository) {
    this.attributeRepository = attributeRepository;
    this.taskRepository = taskRepository;
  }

  @BeforeEach
  void setUp() {
    homeTask = new Task("Home");
  }

  @AfterEach
  void tearDown() {
    taskRepository.delete(homeTask);
  }

  @Test
  void testSavingAttributeToTask() {
    TaskAttribute<String> description = new StringTaskAttribute("description",
        "Simple description");
    TaskAttribute<Integer> priority = new IntegerTaskAttribute("priority", 1);

    homeTask.setAttributes(List.of(description, priority));
    taskRepository.save(homeTask);

    assertThat(attributeRepository.count()).isEqualTo(2L);

    Task savedTask = taskRepository.findById(homeTask.getId()).orElseThrow();
    assertThat(savedTask.getAttributes()).containsExactly(description, priority);
    assertThat(savedTask.getAttributes().getFirst().getAttributeValue()).isEqualTo(
        "Simple description");
  }


}