package lk.spring_security.method_level_security_global_security_exceptions.web.task.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.*;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {
    //requestDTO to domain model
    Task toDomainModel(TaskRequestDTO taskRequestDTO);

    //domain model to responseDTO
    TaskResponseDTO toResponseDTO(Task task);

    /* __GET_ALL_TASKS__ */

    //domain model to response
    GetAllTaskResponseDTO toGetAllTaskResponseDTO(GetAllTaskResult getAllTaskResult);

    /* __CRETE_TASK__ */

    //request to command
    CreateTaskCommand toCreateTaskCommand(Long userId, CreateTaskRequestDTO createTaskRequestDTO);

    //domain mode to response
    CreateTaskResponseDTO toCreateTaskResponseDTO(CreateTaskResult createTaskResult);


    /* __UPDATE_TASK__ */

    //request to command
    UpdateTaskCommand toUpdateTaskCommand(Long userId, Long taskId, UpdateTaskRequestDTO updateTaskRequestDTO);

    //domain model to response
    UpdateTaskResponseDTO toUpdateTaskResponseDTO(UpdateTaskResult updateTaskResult);

}
