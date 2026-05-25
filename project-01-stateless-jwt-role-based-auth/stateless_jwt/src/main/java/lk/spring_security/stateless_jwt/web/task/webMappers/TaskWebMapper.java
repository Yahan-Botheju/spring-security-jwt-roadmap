package lk.spring_security.stateless_jwt.web.task.webMappers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {

    //dto to domain model
    Task toDomainModel(TaskRequestDTO taskRequestDTO);

    //domain model to dto
    @Mapping(target = "userId", source = "userId")
    TaskResponseDTO toResponseDTO(Task task);
}
