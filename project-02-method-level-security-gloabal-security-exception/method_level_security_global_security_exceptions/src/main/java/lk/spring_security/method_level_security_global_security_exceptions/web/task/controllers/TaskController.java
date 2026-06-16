package lk.spring_security.method_level_security_global_security_exceptions.web.task.controllers;

import jakarta.validation.Valid;
import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.webMappers.TaskWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
        List<Task> tasks = taskUseCase.getAllTasks();
        List<TaskResponseDTO> responseDTO =tasks.stream().map(taskWebMapper::toResponseDTO).toList();
        return ResponseEntity.ok(responseDTO);
    }

    //create task
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody TaskRequestDTO taskRequestDTO
    ){
        Long userId = customUserDetails.getUserId();
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(
                taskUseCase.createTask(userId,taskWebMapper.toDomainModel(taskRequestDTO )));
        return ResponseEntity.created(URI.create("/api/v2/tasks")).body(responseDTO);
    }

    //update task
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @Valid @PathVariable Long taskId,
            @Valid @RequestBody TaskRequestDTO taskRequestDTO
    ){
        Task toDomainModel = taskWebMapper.toDomainModel(taskRequestDTO);
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(
                taskUseCase.updateTask(taskId, toDomainModel));

        return ResponseEntity.ok(responseDTO);
    }

    //delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @Valid @PathVariable Long taskId
    ){
        taskUseCase.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted successfully");
    }
}
