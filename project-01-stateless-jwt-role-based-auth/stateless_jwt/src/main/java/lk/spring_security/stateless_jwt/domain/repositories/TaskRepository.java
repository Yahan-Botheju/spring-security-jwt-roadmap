package lk.spring_security.stateless_jwt.domain.repositories;

import lk.spring_security.stateless_jwt.domain.models.Task;

import java.util.List;

public interface TaskRepository {

    //get user tasks list
    List<Task> findByUserUserId(Long userId);

    //get all tasks
    List<Task> getAllTasks();

    //save tasks
    Task saveTask(Task task);
}
