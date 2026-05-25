package lk.spring_security.stateless_jwt.web.task.webMappers;

import javax.annotation.processing.Generated;
import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskResponseDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-23T20:59:44+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class TaskWebMapperImpl implements TaskWebMapper {

    @Override
    public Task toDomainModel(TaskRequestDTO taskRequestDTO) {
        if ( taskRequestDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setTaskTitle( taskRequestDTO.getTaskTitle() );
        task.setTaskDescription( taskRequestDTO.getTaskDescription() );
        if ( taskRequestDTO.getCompleted() != null ) {
            task.setCompleted( taskRequestDTO.getCompleted() );
        }
        task.setUserId( taskRequestDTO.getUserId() );

        return task;
    }

    @Override
    public TaskResponseDTO toResponseDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();

        taskResponseDTO.setUserId( task.getUserId() );
        taskResponseDTO.setTaskId( task.getTaskId() );
        taskResponseDTO.setTaskTitle( task.getTaskTitle() );
        taskResponseDTO.setTaskDescription( task.getTaskDescription() );
        taskResponseDTO.setCompleted( task.isCompleted() );

        return taskResponseDTO;
    }
}
