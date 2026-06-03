package lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs;

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

    @NotBlank(message = "Task Description cannot be empty")
    private String taskDescription;

    @NotNull(message = "Status cannot be empty")
    private Boolean completed;

}
