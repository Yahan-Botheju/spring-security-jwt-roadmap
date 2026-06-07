package lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper.UserPersistenceMapper;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa.JpaUserRepository;

public class UserRepositoryImpl implements UserRepository {

    //inject required classes
    private UserPersistenceMapper userPersistenceMapper;
    private JpaUserRepository jpaUserRepository;


    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper) {
        this.userPersistenceMapper = userPersistenceMapper;
        this.jpaUserRepository = jpaUserRepository;
    }
}
