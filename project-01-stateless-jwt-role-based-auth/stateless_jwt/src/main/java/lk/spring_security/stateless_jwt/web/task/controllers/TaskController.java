package lk.spring_security.stateless_jwt.web.task.controllers;

import jakarta.validation.Valid;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCase;
import lk.spring_security.stateless_jwt.usecase.task.records.*;
import lk.spring_security.stateless_jwt.web.task.DTOs.*;
import lk.spring_security.stateless_jwt.web.task.webMappers.TaskWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    //inject task usecase
    private final TaskUseCase taskUseCase;

    //inject task web mapper
    private final TaskWebMapper taskWebMapper;

    //get all tasks
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetAllTaskResponseDTO>> getAllTasks() {

        List<GetAllTaskResult> tasks =  taskUseCase.getAllTasks();
        List<GetAllTaskResponseDTO> responseDTOS =  tasks.stream()
                .map(taskWebMapper::toGetAllTaskResponseDTO).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }

    //save task
    @PostMapping
    public ResponseEntity<SaveTaskResponseDTO> saveTasks(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody SaveTaskRequestDTO saveTaskRequestDTO
    ){
        SaveTaskCommand taskCommand = taskWebMapper.toSaveTaskCommand(userDetails.getUsername(), saveTaskRequestDTO);
        SaveTaskResult taskResult = taskUseCase.saveTask(taskCommand);
        SaveTaskResponseDTO responseDTO = taskWebMapper.toSaveTaskResponseDTO(taskResult);

        return ResponseEntity.created(URI.create("/api/v1/tasks")).body(responseDTO);
    }

    //update task
    @PutMapping("/{taskId}")
    public ResponseEntity<UpdateTaskResponseDTO> updateTask(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @PathVariable Long taskId,
            @Valid @RequestBody UpdateTaskRequestDTO updateTaskRequestDTO
    ){
        UpdateTaskCommand taskCommand = taskWebMapper
                .toUpdateTaskCommand(userDetails.getUsername(), taskId, updateTaskRequestDTO);
        UpdateTaskResult taskResult = taskUseCase.updateTask(taskCommand);
        UpdateTaskResponseDTO responseDTO = taskWebMapper.toUpdateTaskResponseDTO(taskResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long taskId
    ){
        taskUseCase.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted");
    }
}
