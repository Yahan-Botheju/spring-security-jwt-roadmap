package lk.spring_security.method_level_security_global_security_exceptions.usecase.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;

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
    public List<Task> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    //create task
    @Override
    public Task createTask(Long userId,Task task){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        task.setUser(user);
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
