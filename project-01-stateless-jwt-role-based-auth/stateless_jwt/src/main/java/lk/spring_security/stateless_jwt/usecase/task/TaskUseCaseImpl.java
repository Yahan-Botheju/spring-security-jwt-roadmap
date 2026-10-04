package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;

import java.util.List;

public class TaskUseCaseImpl implements TaskUseCase {

    //inject task domain repo
    private final TaskRepository taskRepository;

    public TaskUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //get all tasks
    @Override
    public List<GetAllTaskResult> getAllTasks(){
        return taskRepository.getAllTasks().stream()
                .map(task -> new GetAllTaskResult(
                        task.getTaskId(),
                        task.getTaskTitle(),
                        task.getTaskDescription(),
                        task.isCompleted(),
                        task.getTaskId()
                )).toList();
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

    @Override
    public void deleteTask(Long taskId){
        taskRepository.deleteTask(taskId);
    }
}
