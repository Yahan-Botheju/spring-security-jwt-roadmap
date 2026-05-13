package lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa;

import lk.spring_security.stateless_jwt.infrastructure.task.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskEntity,Long> {
}
