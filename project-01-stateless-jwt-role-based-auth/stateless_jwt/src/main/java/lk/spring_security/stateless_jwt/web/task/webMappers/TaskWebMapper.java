package lk.spring_security.stateless_jwt.web.task.webMappers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskCommand;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskResult;
import lk.spring_security.stateless_jwt.web.task.DTOs.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskWebMapper {

    //dto to domain model
    Task toDomainModel(TaskRequestDTO taskRequestDTO);

    //domain model to dto
    @Mapping(target = "userId", source = "userId")
    TaskResponseDTO toResponseDTO(Task task);

    /* __GET_ALL_TASKS__ */

    //domain model to response
    GetAllTaskResponseDTO toGetAllTaskResponseDTO(GetAllTaskResult getAllTaskResult);

    /* __CREATE_TASK__ */

    //request to command
    SaveTaskCommand  toSaveTaskCommand(SaveTaskRequestDTO saveTaskRequestDTO);

    //domain model to response
    SaveTaskResponseDTO toSaveTaskResponseDTO(SaveTaskResult saveTaskResult);
}
