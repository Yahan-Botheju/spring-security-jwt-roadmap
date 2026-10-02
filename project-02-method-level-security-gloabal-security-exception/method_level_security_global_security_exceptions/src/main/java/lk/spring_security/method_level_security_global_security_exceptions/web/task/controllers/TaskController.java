package lk.spring_security.method_level_security_global_security_exceptions.web.task.controllers;

import jakarta.validation.Valid;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetails;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.record.*;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.*;
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
    public ResponseEntity<List<GetAllTaskResponseDTO>> getAllTasks(){

        List<GetAllTaskResult> toAllTaskResults = taskUseCase.getAllTasks().stream().toList();
        List<GetAllTaskResponseDTO> responseDTO = toAllTaskResults.stream()
                .map(taskWebMapper::toGetAllTaskResponseDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //create task
    @PostMapping
    public ResponseEntity<CreateTaskResponseDTO> createTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody CreateTaskRequestDTO createTaskRequestDTO
    ){
        Long userId = customUserDetails.getUserId();

        CreateTaskCommand toCommand = taskWebMapper.toCreateTaskCommand(userId, createTaskRequestDTO);
        CreateTaskResult toCreteResult = taskUseCase.createTask(toCommand);
        CreateTaskResponseDTO responseDTO = taskWebMapper.toCreateTaskResponseDTO(toCreteResult);

        return ResponseEntity.created(URI.create("/api/v2/tasks")).body(responseDTO);
    }

    //update task
    @PutMapping("/{taskId}")
    public ResponseEntity<UpdateTaskResponseDTO> updateTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequestDTO updateTaskRequestDTO
    ){

        Long userId = customUserDetails.getUserId();
        UpdateTaskCommand toCommand = taskWebMapper.toUpdateTaskCommand(userId,taskId,updateTaskRequestDTO);
        UpdateTaskResult toCreteResult = taskUseCase.updateTask(toCommand);
        UpdateTaskResponseDTO responseDTO = taskWebMapper.toUpdateTaskResponseDTO(toCreteResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
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
