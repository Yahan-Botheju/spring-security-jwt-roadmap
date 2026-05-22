package lk.spring_security.stateless_jwt.web.task.Controllers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCase;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskRequestDTO;
import lk.spring_security.stateless_jwt.web.task.DTOs.TaskResponseDTO;
import lk.spring_security.stateless_jwt.web.task.webMappers.TaskWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks =  taskUseCase.getAllTasks();
        tasks.stream().map(taskWebMapper::toResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    //save task
    @PostMapping
    public ResponseEntity<String> saveTasks(
            @RequestBody TaskRequestDTO taskRequestDTO
            ){
        Task taskDomainModel = taskUseCase.saveTask(taskWebMapper.toDomainModel(taskRequestDTO));
        TaskResponseDTO responseDTO = taskWebMapper.toResponseDTO(taskDomainModel);
        return ResponseEntity.created(URI.create("/api/v1/tasks")).body(responseDTO.toString());
    }
}
