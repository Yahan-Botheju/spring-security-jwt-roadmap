package lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa;

import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTaskRepository extends JpaRepository<TaskEntity,Long> {

    //create custom query for get user tasks list
    List<TaskEntity> findByUserUserId(Long userId);
}
