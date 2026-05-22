package lk.spring_security.stateless_jwt.web.task.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private Boolean completed;

    private Long userId;
}
