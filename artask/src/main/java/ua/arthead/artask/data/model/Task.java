package ua.arthead.artask.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String title;
  private int durationHours;
  private int durationMinutes;
  private Boolean isLightRequired;
  private LocalTime requiredFromTime;
  private LocalTime requiredToTime;
  private Boolean isRequired;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_task_id")
  private Task parentTask;

  @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> tasks = new ArrayList<>();

  public Task(String title) {
    this.title = title;
  }

  public Task(String title, Task parentTask) {
    this.title = title;
    this.parentTask = parentTask;
  }

  public String getRequiredTime() {
    if (requiredFromTime == null || requiredToTime == null) return "";
    return requiredFromTime + " " + requiredToTime;
  }
}
