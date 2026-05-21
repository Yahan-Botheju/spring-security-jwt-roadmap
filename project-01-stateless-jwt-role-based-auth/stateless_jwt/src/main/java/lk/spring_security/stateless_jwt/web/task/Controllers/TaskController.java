package lk.spring_security.stateless_jwt.web.task.Controllers;

import lk.spring_security.stateless_jwt.web.task.webMappers.TaskWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    //inject task web mapper
    private final TaskWebMapper taskWebMapper;
}
