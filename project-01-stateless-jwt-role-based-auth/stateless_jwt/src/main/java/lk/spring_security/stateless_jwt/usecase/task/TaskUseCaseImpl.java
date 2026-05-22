package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskUseCaseImpl implements TaskUseCase {

    //inject task domain repo
    private final TaskRepository taskRepository;

    //get all tasks
    @Override
    public List<Task> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    //save tasks
    @Override
    public Task saveTask(Task task){
        return taskRepository.saveTask(task);
    }

    //update task
    @Override
    public Task updateTask(Task task, Long taskId){
        return taskRepository.updateTask(task,taskId);
    }
}
