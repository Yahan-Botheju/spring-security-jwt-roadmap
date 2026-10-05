package lk.spring_security.stateless_jwt.usecase.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.usecase.task.records.*;
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
    public List<GetAllTaskResult> getAllTasks() {
        return taskRepository.getAllTasks().stream()
                .map(task -> new GetAllTaskResult(
                        task.getTaskId(),
                        task.getTaskTitle(),
                        task.getTaskDescription(),
                        task.isCompleted(),
                        task.getTaskId()
                )).toList();
    }

    //create new task
    @Override
    public SaveTaskResult saveTask(SaveTaskCommand saveTaskCommand) {

        //check incoming fields
        if (saveTaskCommand.userId() == null
                || saveTaskCommand.taskTitle().isEmpty()
                || saveTaskCommand.taskDescription().isEmpty()
        ) {
            throw new IllegalStateException("Required fields cannot be empty");
        }
        //get user
        User user = userRepository.userFindById(saveTaskCommand.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        //create new task with related user through domain model
        Task newTask = Task.createNewTask(
                saveTaskCommand.taskTitle(),
                saveTaskCommand.taskDescription(),
                false,
                user.getUserId()
        );
        //save task
        Task savedTask = taskRepository.saveTask(newTask);

        return new SaveTaskResult(
                savedTask.getTaskId(),
                saveTaskCommand.taskTitle(),
                saveTaskCommand.taskDescription(),
                savedTask.isCompleted(),
                savedTask.getUserId()
        );
    }

    //update task
    @Override
    public UpdateTaskResult updateTask(UpdateTaskCommand updateTaskCommand) {

        //check incoming fields
        if (updateTaskCommand.email().isEmpty()
                || updateTaskCommand.taskId() == null
                || updateTaskCommand.taskTitle().isEmpty()
                || updateTaskCommand.taskDescription().isEmpty()
                || updateTaskCommand.completed() == null
        ) {
            throw new IllegalStateException("Required fields cannot be empty");
        }
        //get task
        Task task = taskRepository.findByTaskId(updateTaskCommand.taskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        //get user
        User user = userRepository.userFindByEmail(updateTaskCommand.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        //update tasks through domain
        task.updateTask(
                updateTaskCommand.taskTitle(),
                updateTaskCommand.taskDescription(),
                updateTaskCommand.completed(),
                user.getUserId()
        );

        Task savedTask = taskRepository.saveTask(task);

        return new  UpdateTaskResult(
                savedTask.getTaskId(),
                savedTask.getTaskTitle(),
                savedTask.getTaskDescription(),
                savedTask.isCompleted(),
                savedTask.getUserId()
        );
    }


    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteTask(taskId);
    }
}




































