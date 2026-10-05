package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskCommand;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskResult;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public class TaskUseCaseImpl implements TaskUseCase {

    //inject task domain repo
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskUseCaseImpl(
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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

    @Override
    public SaveTaskResult saveTask(SaveTaskCommand saveTaskCommand) {

        if(saveTaskCommand.userId() == null
                || saveTaskCommand.taskTitle().isEmpty()
                || saveTaskCommand.taskDescription().isEmpty()
        ) {
            throw new IllegalStateException("Required fields cannot be empty");
        }

        Task newTask = Task.createNewTask(
                saveTaskCommand.taskTitle(),
                saveTaskCommand.taskDescription(),
                false
        );

        return null;
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
