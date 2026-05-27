package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.taskPersistenceMapper;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    //domain model to entity
    TaskEntity toEntity(Task task);

    //entity to domain model
    Task toDomainModel(TaskEntity taskEntity);
}
