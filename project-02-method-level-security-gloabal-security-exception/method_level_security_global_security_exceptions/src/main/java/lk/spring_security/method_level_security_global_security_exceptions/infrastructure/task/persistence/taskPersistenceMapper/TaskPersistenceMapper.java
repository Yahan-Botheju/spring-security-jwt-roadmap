package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.taskPersistenceMapper;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.*;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    //domain model to entity
    @Mapping(target = "user.userId", source = "userId")
    TaskEntity toEntity(Task task);

    //entity to domain model
    @Mapping(target = "userId", source = "user.userId")
    Task toDomainModel(TaskEntity taskEntity);

    //update task
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaskEntity updateTaskEntity(Task task, @MappingTarget  TaskEntity taskEntity);
}
