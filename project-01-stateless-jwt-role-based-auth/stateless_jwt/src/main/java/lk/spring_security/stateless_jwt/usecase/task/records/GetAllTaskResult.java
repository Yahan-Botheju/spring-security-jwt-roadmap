package lk.spring_security.stateless_jwt.usecase.task.records;

public record GetAllTaskResult(
        Long taskId,
        String taskTitle,
        String  taskDescription,
        boolean completed,
        Long userId
) {
}
