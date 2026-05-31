package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;

import java.util.List;

public interface TaskUseCase {
    //get all task
    List<Task> getAllTasks();

    //create task
    Task createTask(Task task);

    //update task
    Task updateTask(Long taskId, Task task);

    //delete task
    void  deleteTask(Long taskId);
}
