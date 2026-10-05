package lk.spring_security.stateless_jwt.infrastructure.task;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers.TaskPersistenceMapper;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;

import java.util.List;
import java.util.Optional;

public class TaskPersistenceImpl implements TaskRepository {

    //inject required dependencies
    private final JpaTaskRepository jpaTaskRepository;
    private final TaskPersistenceMapper taskPersistenceMapper;
    private final JpaUserRepository jpaUserRepository;

    public TaskPersistenceImpl(
            JpaTaskRepository jpaTaskRepository,
            TaskPersistenceMapper taskPersistenceMapper,
            JpaUserRepository jpaUserRepository
    ) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskPersistenceMapper = taskPersistenceMapper;
        this.jpaUserRepository = jpaUserRepository;
    }

    //task find by its id
    @Override
    public Optional<Task> findByTaskId(Long taskId) {
        return jpaTaskRepository.findByTaskId(taskId)
                .map(taskPersistenceMapper::toDomainModel);
    }

    //get user tasks list
    @Override
    public List<Task> findByUserUserId(Long userId) {
        return jpaTaskRepository.findByUserUserId(userId).stream()
                .map(taskPersistenceMapper::toDomainModel).toList();
    }

    //get all tasks
    @Override
    public List<Task> getAllTasks() {
        List<TaskEntity> taskEntities = jpaTaskRepository.findAll();
        return taskEntities.stream().map(taskPersistenceMapper::toDomainModel).toList();
    }

    //save tasks
    @Override
    public Task saveTask(Task task){
        TaskEntity taskEntity = taskPersistenceMapper.toEntity(task);
        UserEntity userEntity = jpaUserRepository.findById(task.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        taskEntity.setUser(userEntity);
        TaskEntity savedTaskEntity = jpaTaskRepository.save(taskEntity);
        return taskPersistenceMapper.toDomainModel(savedTaskEntity);
    }

    //update task
    @Override
    public Task updateTask(Task task, Long taskId){
        TaskEntity taskEntity = jpaTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        UserEntity userEntity = jpaUserRepository.findById(task.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TaskEntity turnToEntity =  taskPersistenceMapper.updateTask(task, taskEntity);
        turnToEntity.setUser(userEntity);
        TaskEntity savedTaskEntity = jpaTaskRepository.save(turnToEntity);
        return taskPersistenceMapper.toDomainModel(savedTaskEntity);
    }

    //delete task
    @Override
    public void deleteTask(Long taskId){
        TaskEntity taskEntity = jpaTaskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        jpaTaskRepository.delete(taskEntity);
    }
}
