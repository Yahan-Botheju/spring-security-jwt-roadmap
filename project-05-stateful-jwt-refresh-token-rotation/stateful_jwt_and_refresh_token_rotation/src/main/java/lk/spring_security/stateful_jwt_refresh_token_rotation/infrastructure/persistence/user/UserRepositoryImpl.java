package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.mappers.UserPersistenceMapper;

public class UserRepositoryImpl implements UserRepository {

    //inject required dependencies
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;


    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaUserRepository=jpaUserRepository;
        this.userPersistenceMapper=userPersistenceMapper;
    }
}
