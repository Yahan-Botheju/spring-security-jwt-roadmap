package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper.UserPersistenceMapper;

public class UserPersistenceImpl implements UserRepository {

    //inject user jpa repo
    private final JpaTaskRepository jpaTaskRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;


    public UserPersistenceImpl(
            JpaTaskRepository taskRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaTaskRepository = taskRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }
}
