package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;

public class TaskUseCaseImpl implements TaskUseCase{

    //inject task repo
    private final TaskRepository taskRepository;

    public TaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
