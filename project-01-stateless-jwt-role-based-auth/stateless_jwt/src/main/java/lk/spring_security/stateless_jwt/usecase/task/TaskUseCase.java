package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;

import java.util.List;

public interface TaskUseCase {

    //get all tasks
    List<GetAllTaskResult> getAllTasks();

    //save tasks
    Task saveTask(Task task);

    //update task
    Task updateTask(Task task, Long taskId);

    //delete task
    void deleteTask(Long taskId);
}
