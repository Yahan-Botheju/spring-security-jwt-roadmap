package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.CreateTaskCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.CreateTaskResult;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.GetAllTaskResult;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.security.InvalidParameterException;
import java.util.List;

public class TaskUseCaseImpl implements TaskUseCase{

    //inject required classes
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskUseCaseImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    //get all task
    @Override
    public List<GetAllTaskResult> getAllTasks(){

        return taskRepository.getAllTasks().stream().map(
                task -> new GetAllTaskResult(
                        task.getTaskId(),
                        task.getTaskTitle(),
                        task.getTaskDescription(),
                        task.isCompleted(),
                        task.getUserId()
                )
        ).toList();
    }

    //create task
    @Override
    public CreateTaskResult createTask(CreateTaskCommand createTaskCommand) {

        //validate incoming fields
        if(createTaskCommand.userId() == null
                || createTaskCommand.taskTitle().isBlank()
                || createTaskCommand.taskDescription().isBlank()
        ){
            throw new InvalidParameterException("Required fields are empty");
        }
        //get user
        User user = userRepository.findById(createTaskCommand.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //create task model using domain
        Task newTask = Task.createNewTask(
                createTaskCommand.taskTitle(),
                createTaskCommand.taskDescription(),
                false,
                createTaskCommand.userId(),
                user
        );

        Task savedTask = taskRepository.createTask(newTask);

        return new CreateTaskResult(
                savedTask.getTaskId(),
                savedTask.getTaskTitle(),
                savedTask.getTaskDescription(),
                savedTask.isCompleted(),
                savedTask.getUserId()
        );
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
























