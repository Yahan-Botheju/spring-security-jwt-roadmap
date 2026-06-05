package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task;

import jakarta.persistence.EntityNotFoundException;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.taskPersistenceMapper.TaskPersistenceMapper;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.entity.UserEntity;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa.JpaUserRepository;

import java.util.List;

public class TaskPersistenceImpl implements TaskRepository {

    //inject required classes
    private final JpaTaskRepository jpaTaskRepository;
    private final TaskPersistenceMapper taskPersistenceMapper;
    private final JpaUserRepository jpaUserRepository;

    public TaskPersistenceImpl(
            JpaTaskRepository taskRepository,
            TaskPersistenceMapper taskPersistenceMapper,
            JpaUserRepository jpaUserRepository
    ) {
        this.jpaTaskRepository = taskRepository;
        this.taskPersistenceMapper = taskPersistenceMapper;
        this.jpaUserRepository = jpaUserRepository;
    }

    //get all task
    public List<Task> getAllTasks(){
        List<TaskEntity> entityList = jpaTaskRepository.findAll();
        return entityList.stream().map(taskPersistenceMapper::toDomainModel).toList();
    }

    //create task
    public Task createTask(Long userId, Task task){
        UserEntity availableUserEntity = jpaUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        TaskEntity taskEntity = taskPersistenceMapper.toEntity(task);
        taskEntity.setUser(availableUserEntity);
        TaskEntity savedEntity = jpaTaskRepository.save(taskEntity);
        return taskPersistenceMapper.toDomainModel(savedEntity);
    }

    //update task
    public Task updateTask(Long taskId, Task task){
        TaskEntity existingEntity = jpaTaskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        TaskEntity updatedTaskEntity = taskPersistenceMapper.updateTaskEntity(task, existingEntity);
        return taskPersistenceMapper.toDomainModel(updatedTaskEntity);
    }

    //delete task
    public void deleteTask(Long taskId){
        TaskEntity existingTask = jpaTaskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        jpaTaskRepository.delete(existingTask);
    }
}
