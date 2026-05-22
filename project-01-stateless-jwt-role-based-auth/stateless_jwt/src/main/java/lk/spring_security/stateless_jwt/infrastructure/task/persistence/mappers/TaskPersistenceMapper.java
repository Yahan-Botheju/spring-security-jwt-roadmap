package lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    //domain model to entity
    @Mapping(target = "user.userId", source = "userId")
    TaskEntity toEntity(Task task);

    //entity to domain mode
    @Mapping(target = "userId", source = "user.userId")
    Task toDomainModel(TaskEntity taskEntity);

    //update task
    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaskEntity updateTask(Task task, @MappingTarget TaskEntity taskEntity);
}
