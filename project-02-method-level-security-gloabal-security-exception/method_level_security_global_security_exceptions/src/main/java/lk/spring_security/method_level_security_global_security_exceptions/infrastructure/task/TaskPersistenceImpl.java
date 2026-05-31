package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.taskPersistenceMapper.TaskPersistenceMapper;

import java.util.List;

public class TaskPersistenceImpl implements TaskRepository {

    //inject required classes
    private final JpaTaskRepository jpaTaskRepository;
    private final TaskPersistenceMapper taskPersistenceMapper;


    public TaskPersistenceImpl(
            JpaTaskRepository taskRepository,
            TaskPersistenceMapper taskPersistenceMapper
    ) {
        this.jpaTaskRepository = taskRepository;
        this.taskPersistenceMapper = taskPersistenceMapper;
    }

    //get all task
    public List<Task> getAllTasks(){
        List<TaskEntity> entityList = jpaTaskRepository.findAll();
        return entityList.stream().map()
    }
}
