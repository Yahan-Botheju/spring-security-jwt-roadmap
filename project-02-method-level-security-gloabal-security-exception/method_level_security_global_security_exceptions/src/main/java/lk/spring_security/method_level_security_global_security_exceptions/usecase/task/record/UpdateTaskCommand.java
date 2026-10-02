package lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record;

public record UpdateTaskCommand(
        Long taskId,
        String taskTitle,
        String taskDescription,
        boolean isCompleted
) {
}
