package lk.spring_security.stateless_jwt.usecase.task.records;

public record SaveTaskResult(
        Long taskId,
        String taskTitle,
        String  taskDescription,
        boolean completed,
        Long userId
) {
}
