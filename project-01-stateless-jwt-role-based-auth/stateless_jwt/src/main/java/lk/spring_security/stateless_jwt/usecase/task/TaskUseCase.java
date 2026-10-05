package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskCommand;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskResult;

import java.util.List;

public interface TaskUseCase {

    //get all tasks
    List<GetAllTaskResult> getAllTasks();

    //save tasks
    SaveTaskResult saveTask(SaveTaskCommand saveTaskCommand);

    //update task
    Task updateTask(Task task, Long taskId);

    //delete task
    void deleteTask(Long taskId);
}
