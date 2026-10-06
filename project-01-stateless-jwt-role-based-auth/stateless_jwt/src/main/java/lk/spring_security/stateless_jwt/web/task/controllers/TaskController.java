package lk.spring_security.stateless_jwt.web.task.controllers;

import jakarta.validation.Valid;
import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCase;
import lk.spring_security.stateless_jwt.usecase.task.records.GetAllTaskResult;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskCommand;
import lk.spring_security.stateless_jwt.usecase.task.records.SaveTaskResult;
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
        List<GetAllTaskResponseDTO> responseDTOS =  tasks.stream().map(taskWebMapper::toGetAllTaskResponseDTO).toList();

        return ResponseEntity.ok(responseDTOS);
    }

    //save task
    @PostMapping
    public ResponseEntity<SaveTaskResponseDTO> saveTasks(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody SaveTaskRequestDTO saveTaskRequestDTO
    ){
        String email = userDetails.getUsername();
        SaveTaskCommand taskCommand = taskWebMapper.toSaveTaskCommand(email, saveTaskRequestDTO);
        SaveTaskResult taskResult = taskUseCase.saveTask(taskCommand);
        SaveTaskResponseDTO responseDTO = taskWebMapper.toSaveTaskResponseDTO(taskResult);

        return ResponseEntity.created(URI.create("/api/v1/tasks")).body(responseDTO);
    }

    //update task
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequestDTO taskRequestDTO
    ){
        Task toDomainModel = taskUseCase.updateTask(taskWebMapper.toDomainModel(taskRequestDTO), taskId);
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(toDomainModel);
        return ResponseEntity.ok(responseDTO);
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
