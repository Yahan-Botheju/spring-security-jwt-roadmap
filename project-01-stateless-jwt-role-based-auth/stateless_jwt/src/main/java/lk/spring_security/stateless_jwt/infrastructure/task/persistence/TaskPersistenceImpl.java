package lk.spring_security.stateless_jwt.infrastructure.task.persistence;

import lk.spring_security.stateless_jwt.domain.models.Task;
import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers.TaskPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskPersistenceImpl implements TaskRepository {

    //inject task jpa repo
    private final JpaTaskRepository jpaTaskRepository;

    //inject task persistence mapper
    private final TaskPersistenceMapper taskPersistenceMapper;

    /* ----- HELPER METHOD ----- */

    //get user tasks list
    public List<Task> findByUser_Id(Long userId) {
        return jpaTaskRepository.findByUserUserId(userId).stream()
                .map(taskPersistenceMapper::toDomainModel).toList();
    }
}
