package lk.spring_security.refresh_token.infrastructure.user;

import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.infrastructure.user.jpa.JpaUserRepository;
import lk.spring_security.refresh_token.infrastructure.user.mappers.UserPersistenceMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    //inject required dependencies
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;


    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    /* ----- __HELPER_METHOD__ ----- */


    //user find by email
    @Override
    public Optional<User> findByEmail(String email){
        return jpaUserRepository.findByEmail(email).map(userPersistenceMapper::toDomainMode);
    }

    //user find by ID
    @Override
    public Optional<User> findById(Long userId){
        return jpaUserRepository.findById(userId).map(userPersistenceMapper::toDomainMode);
    }
}
