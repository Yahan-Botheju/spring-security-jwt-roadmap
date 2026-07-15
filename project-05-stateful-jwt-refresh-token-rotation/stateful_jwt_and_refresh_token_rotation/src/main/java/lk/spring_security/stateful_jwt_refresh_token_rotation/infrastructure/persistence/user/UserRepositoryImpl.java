package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.entities.UserEntity;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.mappers.UserPersistenceMapper;

import java.util.Optional;

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

    //get user by email
    @Override
    public Optional<User> findByEmail(String email){
        return jpaUserRepository.findByEmail(email).map(userPersistenceMapper::toDomainModel);
    }

    //register user
    @Override
    public User registerUser(User user){
        if(jpaUserRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("User with email already exists");
        }
        UserEntity toEntity = userPersistenceMapper.toEntity(user);
        UserEntity savedUser = jpaUserRepository.save(toEntity);

        return  userPersistenceMapper.toDomainModel(savedUser);
    }
}
