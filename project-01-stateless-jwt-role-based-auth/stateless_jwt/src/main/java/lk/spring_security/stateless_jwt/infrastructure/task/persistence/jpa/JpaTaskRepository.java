package lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa;

import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTaskRepository extends JpaRepository<TaskEntity,Long> {

    //create custom query for get user tasks list
    List<TaskEntity> findByUserUserId(Long userId);

    //task find by its id
    Optional<TaskEntity> findByTaskId(Long taskId);
}
