package lk.spring_security.stateless_jwt.web.task.Controllers;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCase;
import lk.spring_security.stateless_jwt.web.task.webMappers.TaskWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Task> tasks =
    }
}
