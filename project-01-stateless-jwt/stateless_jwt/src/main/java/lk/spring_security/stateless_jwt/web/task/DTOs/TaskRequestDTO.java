package lk.spring_security.stateless_jwt.web.task.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
    @NotBlank(message = "Task Title cannot be empty")
    private String taskTitle;
    @NotBlank(message = "Task description cannot be empty")
    private String taskDescription;
    @NotNull(message = "Complete status cannot be empty")
    private Boolean completed;
}
