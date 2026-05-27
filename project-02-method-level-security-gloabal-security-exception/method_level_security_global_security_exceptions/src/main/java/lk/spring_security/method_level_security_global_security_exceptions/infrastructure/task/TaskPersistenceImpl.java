package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;

public class TaskPersistenceImpl implements TaskRepository {

    //inject jpaTaskRepo
    private final JpaTaskRepository jpaTaskRepository;

    public TaskPersistenceImpl(JpaTaskRepository taskRepository) {
        this.jpaTaskRepository = taskRepository;
    }
}
