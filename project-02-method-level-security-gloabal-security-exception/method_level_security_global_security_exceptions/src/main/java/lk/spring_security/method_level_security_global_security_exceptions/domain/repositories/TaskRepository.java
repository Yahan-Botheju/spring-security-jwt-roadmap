package lk.spring_security.method_level_security_global_security_exceptions.domain.repositories;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;

import java.util.List;

public interface TaskRepository {
    //get all task
    List<Task> getAllTasks();

    //create task
    Task createTask(Long userId,Task task);

    //update task
    Task updateTask(Long taskId, Task task);

    //delete task
    void  deleteTask(Long taskId);
}
