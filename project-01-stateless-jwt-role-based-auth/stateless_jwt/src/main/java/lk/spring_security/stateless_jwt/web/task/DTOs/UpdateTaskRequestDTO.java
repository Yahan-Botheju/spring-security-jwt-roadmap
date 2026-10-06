package lk.spring_security.stateless_jwt.web.task.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequestDTO {
    @NotBlank(message = "Task Title cannot be empty")
    private String taskTitle;
    @NotBlank(message = "Task Description cannot be empty")
    private String taskDescription;
    @NotBlank(message = "Task Status cannot be empty")
    private Boolean completed;
}
