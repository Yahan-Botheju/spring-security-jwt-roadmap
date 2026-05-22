package lk.spring_security.stateless_jwt.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long taskId;
    private String taskTitle;
    private String  taskDescription;
    private boolean completed;

    private Long userId;
}
