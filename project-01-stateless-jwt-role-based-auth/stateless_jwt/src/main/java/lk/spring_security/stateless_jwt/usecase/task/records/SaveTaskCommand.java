package lk.spring_security.stateless_jwt.usecase.task.records;

public record SaveTaskCommand(
        String taskTitle,
        String taskDescription,
        Boolean completed
) {
}
