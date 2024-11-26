package ua.arthead.artask.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.arthead.artask.data.model.Task;
import ua.arthead.artask.service.TaskService;

@Controller
@AllArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping({"/tasks", "/tasks/{id}"})
  public String getTaskPage(@PathVariable(required = false) Long id, Model model) {
    Task homeTask = taskService.getTaskById(1).orElseThrow();
    model.addAttribute("homeTask", homeTask);
    model.addAttribute("task", id == null ? homeTask : taskService.getTaskById(id).orElseThrow());
    return "tasks";
  }


  @GetMapping("/addTask")
  public String addTaskPage(@RequestParam(required = false) Long id, Model model) {
    Task parentTask = taskService.getTaskById(id == null ? 1 : id).orElseThrow();
    Task newTask = new Task();
    newTask.setParentTask(parentTask);
    model.addAttribute("newTask", newTask);
    return "task";
  }

  @PostMapping("/task")
  public String addTask(@ModelAttribute("task") Task task) {
    task.setParentTask(taskService.getTaskById(task.getParentTask().getId()).orElseThrow());
    taskService.saveTask(task);
    return "redirect:/tasks";
  }

  @DeleteMapping("/task")
  public String deleteTaskPage(@ModelAttribute("task") Task task) {
    return "task";
  }
}
