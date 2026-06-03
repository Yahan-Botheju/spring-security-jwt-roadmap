package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;

import java.util.List;

public class TaskUseCaseImpl implements TaskUseCase{

    //inject task repo
    private final TaskRepository taskRepository;

    public TaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //get all task
    @Override
    public List<Task> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    //create task
    @Override
    public Task createTask(Task task){
        return taskRepository.createTask(task);
    }

    //update task
    @Override
    public Task updateTask(Long taskId, Task task){
        return taskRepository.updateTask(taskId, task);
    }

    //delete task
    @Override
    public void  deleteTask(Long taskId){
        taskRepository.deleteTask(taskId);
    }
}
