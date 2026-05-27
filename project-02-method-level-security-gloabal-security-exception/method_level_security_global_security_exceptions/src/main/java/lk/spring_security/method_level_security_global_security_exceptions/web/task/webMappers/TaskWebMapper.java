package lk.spring_security.method_level_security_global_security_exceptions.web.task.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {
    //requestDTO to domain model
    Task toDomainModel(TaskRequestDTO taskRequestDTO);

    //domain model to responseDTO
    TaskResponseDTO toResponseDTO(Task task);

}
