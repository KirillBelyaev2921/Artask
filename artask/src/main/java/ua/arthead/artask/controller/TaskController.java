package ua.arthead.artask.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.service.TaskService;

@Controller
@AllArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping("/tasks")
  public String getTaskPage(Model model) {
    model.addAttribute("homeTask", taskService.getTaskById(0).orElseThrow());
    return "tasks";
  }

  @PostMapping("/task")
  public String addTaskPage(@ModelAttribute("task") Task task) {
    return "task";
  }

  @DeleteMapping("/task")
  public String deleteTaskPage(@ModelAttribute("task") Task task) {
    return "task";
  }
}
