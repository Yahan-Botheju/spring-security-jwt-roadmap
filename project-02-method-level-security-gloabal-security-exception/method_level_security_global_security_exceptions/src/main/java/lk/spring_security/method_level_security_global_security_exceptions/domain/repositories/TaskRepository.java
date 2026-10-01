package lk.spring_security.method_level_security_global_security_exceptions.domain.repositories;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    //check task existence by id
    Optional<Task> taskFindById(Long taskId);

    //get all task
    List<Task> getAllTasks();

    //create task
    Task createTask(Task task);

    //update task
    Task updateTask(Task task);

    //delete task
    void  deleteTask(Long taskId);
}
