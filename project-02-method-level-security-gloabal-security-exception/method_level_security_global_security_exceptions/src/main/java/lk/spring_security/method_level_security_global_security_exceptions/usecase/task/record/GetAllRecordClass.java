package lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record;

public record GetAllRecordClass(
        Long taskId,
        String taskTitle,
        String taskDescription,
        Boolean completed,
        Long userId
) {
}
