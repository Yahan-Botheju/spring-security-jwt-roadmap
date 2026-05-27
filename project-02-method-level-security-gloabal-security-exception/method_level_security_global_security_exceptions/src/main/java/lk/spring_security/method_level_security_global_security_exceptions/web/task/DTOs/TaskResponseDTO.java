package lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs;

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
