package lk.spring_security.method_level_security_global_security_exceptions.web.task.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.webMappers.TaskWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v2/tasks")
public class TaskController {

    //inject required classes via constructor injection
    private final TaskUseCase taskUseCase;
    private final TaskWebMapper taskWebMapper;

    public TaskController(
            TaskUseCase taskUseCase,
            TaskWebMapper taskWebMapper
    ) {
        this.taskUseCase = taskUseCase;
        this.taskWebMapper = taskWebMapper;
    }

    //get all tasks
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
        List<Task> tasks = taskUseCase.getAllTasks();
        List<TaskResponseDTO> responseDTO =tasks.stream().map(taskWebMapper::toResponseDTO).toList();
        return ResponseEntity.ok(responseDTO);
    }

    //create task
    @PostMapping
    public ResponseEntity<String> createTask(
            @RequestBody TaskRequestDTO taskRequestDTO
    ){
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(
                taskUseCase.createTask(
                        taskWebMapper.toDomainModel(
                                taskRequestDTO
                        )));

        return ResponseEntity.created(URI.create("/api/v2/tasks")).body(responseDTO.toString());
    }

    //update task
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequestDTO taskRequestDTO
    ){
        Task toDomainModel = taskWebMapper.toDomainModel(taskRequestDTO);
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(
                taskUseCase.updateTask(taskId, toDomainModel));

        return ResponseEntity.ok(responseDTO);
    }

    //delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long taskId
    ){
        taskUseCase.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted successfully");
    }
}
