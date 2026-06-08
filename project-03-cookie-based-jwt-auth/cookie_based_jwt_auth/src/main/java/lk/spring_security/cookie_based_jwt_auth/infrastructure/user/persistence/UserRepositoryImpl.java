package lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper.UserPersistenceMapper;


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
