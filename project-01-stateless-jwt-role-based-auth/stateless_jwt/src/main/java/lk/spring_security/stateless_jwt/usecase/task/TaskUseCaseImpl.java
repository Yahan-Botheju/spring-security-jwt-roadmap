package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {

    //inject task domain repo
    private final TaskRepository taskRepository;
}
