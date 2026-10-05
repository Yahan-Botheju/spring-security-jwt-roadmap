package lk.spring_security.stateless_jwt.web.task.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTaskRequestDTO {
    @NotNull(message = "Task ID cannot be empty")
    private Long taskId;
}
