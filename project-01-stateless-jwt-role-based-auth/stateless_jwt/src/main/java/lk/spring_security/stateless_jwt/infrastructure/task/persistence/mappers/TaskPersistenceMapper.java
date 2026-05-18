package lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    //domain model to entity
    TaskEntity toEntity(Task task);

    //entity to domain mode
    Task toDomainModel(TaskEntity taskEntity);
}
