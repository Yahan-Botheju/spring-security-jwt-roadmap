package lk.spring_security.stateless_jwt.domain.repositories;

import lk.spring_security.stateless_jwt.domain.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    //task find by its id
    Optional<Task> findByTaskId(Long taskId);

    //get user tasks list
    List<Task> findByUserUserId(Long userId);

    //get all tasks
    List<Task> getAllTasks();

    //save tasks
    Task saveTask(Task task);

    //update task
    Task updateTask(Task task, Long taskId);

    //delete task
    void deleteTask(Long taskId);
}
