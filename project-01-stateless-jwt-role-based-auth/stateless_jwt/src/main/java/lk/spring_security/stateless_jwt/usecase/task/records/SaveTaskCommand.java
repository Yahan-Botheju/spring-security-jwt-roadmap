package lk.spring_security.stateless_jwt.usecase.task.records;

public record SaveTaskCommand(
        Long userId,
        String taskTitle,
        String taskDescription
) {
}
