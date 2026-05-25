package lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers;

import javax.annotation.processing.Generated;
import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T11:38:53+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class TaskPersistenceMapperImpl implements TaskPersistenceMapper {

    @Override
    public TaskEntity toEntity(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskEntity.TaskEntityBuilder taskEntity = TaskEntity.builder();

        taskEntity.user( taskToUserEntity( task ) );
        taskEntity.taskId( task.getTaskId() );
        taskEntity.taskTitle( task.getTaskTitle() );
        taskEntity.taskDescription( task.getTaskDescription() );
        taskEntity.completed( task.isCompleted() );

        return taskEntity.build();
    }

    @Override
    public Task toDomainModel(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }

        Task task = new Task();

        task.setUserId( taskEntityUserUserId( taskEntity ) );
        task.setTaskId( taskEntity.getTaskId() );
        task.setTaskTitle( taskEntity.getTaskTitle() );
        task.setTaskDescription( taskEntity.getTaskDescription() );
        if ( taskEntity.getCompleted() != null ) {
            task.setCompleted( taskEntity.getCompleted() );
        }

        return task;
    }

    @Override
    public TaskEntity updateTask(Task task, TaskEntity taskEntity) {
        if ( task == null ) {
            return taskEntity;
        }

        if ( task.getTaskId() != null ) {
            taskEntity.setTaskId( task.getTaskId() );
        }
        if ( task.getTaskTitle() != null ) {
            taskEntity.setTaskTitle( task.getTaskTitle() );
        }
        if ( task.getTaskDescription() != null ) {
            taskEntity.setTaskDescription( task.getTaskDescription() );
        }
        taskEntity.setCompleted( task.isCompleted() );

        return taskEntity;
    }

    protected UserEntity taskToUserEntity(Task task) {
        if ( task == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( task.getUserId() );

        return userEntity.build();
    }

    private Long taskEntityUserUserId(TaskEntity taskEntity) {
        if ( taskEntity == null ) {
            return null;
        }
        UserEntity user = taskEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
