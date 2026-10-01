package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.*;

import java.util.List;

public interface TaskUseCase {
    //get all task
    List<GetAllTaskResult> getAllTasks();

    //create task
    CreateTaskResult createTask(CreateTaskCommand createTaskCommand);

    //update task
    UpdateTaskResult updateTask(UpdateTaskCommand updateTaskCommand);

    //delete task
    void  deleteTask(Long taskId);
}
