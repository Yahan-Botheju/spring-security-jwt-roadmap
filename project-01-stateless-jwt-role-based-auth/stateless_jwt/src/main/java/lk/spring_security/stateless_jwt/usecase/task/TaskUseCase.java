package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.usecase.task.records.*;

import java.util.List;

public interface TaskUseCase {

    //get all tasks
    List<GetAllTaskResult> getAllTasks();

    //save tasks
    SaveTaskResult saveTask(SaveTaskCommand saveTaskCommand);

    //update task
    UpdateTaskResult updateTask(UpdateTaskCommand updateTaskCommand);

    //delete task
    void deleteTask(Long taskId);
}
