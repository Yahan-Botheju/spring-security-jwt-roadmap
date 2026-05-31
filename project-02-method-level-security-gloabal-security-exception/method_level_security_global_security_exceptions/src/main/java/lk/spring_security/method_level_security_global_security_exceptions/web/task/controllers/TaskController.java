package lk.spring_security.method_level_security_global_security_exceptions.web.task.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.Task;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.DTOs.TaskResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.task.webMappers.TaskWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
