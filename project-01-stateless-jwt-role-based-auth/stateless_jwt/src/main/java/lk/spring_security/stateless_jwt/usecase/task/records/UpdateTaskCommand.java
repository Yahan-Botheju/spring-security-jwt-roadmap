package lk.spring_security.stateless_jwt.usecase.task.records;

public record UpdateTaskCommand(
        String taskTitle,
        String taskDescription,
        Boolean completed
) {
}
