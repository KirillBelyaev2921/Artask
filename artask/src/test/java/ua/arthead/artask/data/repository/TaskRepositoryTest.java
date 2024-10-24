package ua.arthead.artask.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.arthead.artask.data.model.Task;

@DataJpaTest
class TaskRepositoryTest {

  private TaskRepository taskRepository;
  private Task homeTask;

  @Autowired
  public void setTaskRepository(TaskRepository taskRepository) {
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
  void testSavingHomeTask() {

    Task savedHomeTask = taskRepository.save(homeTask);

    assertThat(savedHomeTask).isNotNull();
    assertThat(savedHomeTask.getId()).isEqualTo(homeTask.getId());
    assertThat(savedHomeTask.getTitle()).isEqualTo(homeTask.getTitle());
  }

  @Test
  void testSavingHomeChildrenTasks() {

    Task firstTask = new Task("First", homeTask);
    Task secondTask = new Task("Second", homeTask);
    Task thirdTask = new Task("Third", homeTask);

    List<Task> homeChildren = List.of(firstTask, secondTask, thirdTask);
    homeTask.setTasks(homeChildren);

    Task savedHomeTask = taskRepository.save(homeTask);

    assertThat(savedHomeTask).isNotNull();
    assertThat(savedHomeTask.getTasks()).hasSize(homeChildren.size());
    assertThat(savedHomeTask.getTasks()).containsExactly(homeChildren.toArray(Task[]::new));
  }

  @Test
  void testSavingSubTasks() {

    Task generalTask = new Task("General", homeTask);
    Task firstTask = new Task("First", generalTask);
    Task secondTask = new Task("Second", generalTask);
    Task thirdTask = new Task("Third", generalTask);

    List<Task> generalChildren = List.of(firstTask, secondTask, thirdTask);
    generalTask.setTasks(generalChildren);
    homeTask.setTasks(List.of(generalTask));

    Task savedHomeTask = taskRepository.save(homeTask);

    assertThat(savedHomeTask).isNotNull();
    assertThat(taskRepository.findAll()).hasSize(5);
    assertThat(savedHomeTask.getTasks()).hasSize(1);
    assertThat(savedHomeTask.getTasks()).containsExactly(generalTask);
    assertThat(savedHomeTask.getTasks().getFirst().getTasks()).hasSize(generalChildren.size());
    assertThat(savedHomeTask.getTasks().getFirst().getTasks()).containsExactly(generalChildren.toArray(Task[]::new));
  }
}