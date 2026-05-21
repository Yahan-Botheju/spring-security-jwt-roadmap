package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;

import java.util.List;

public interface TaskUseCase {

    //get all tasks
    List<Task> getAllTasks();
}
