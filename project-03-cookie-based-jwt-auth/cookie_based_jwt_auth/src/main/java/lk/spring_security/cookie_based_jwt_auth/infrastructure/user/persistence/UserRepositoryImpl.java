package lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.entities.UserEntity;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper.UserPersistenceMapper;

import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {

    //inject required classes
    private final UserPersistenceMapper userPersistenceMapper;
    private final JpaUserRepository jpaUserRepository;


    public UserRepositoryImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper) {
        this.userPersistenceMapper = userPersistenceMapper;
        this.jpaUserRepository = jpaUserRepository;
    }

    /* ----- __HELPER_METHODS__ ----- */
    @Override
    public Optional<User> userFindByEmail(String email){
        return jpaUserRepository.findByEmail(email).map(userPersistenceMapper::toDomainModel);
    }

    /* ----- __PUBLIC_METHODS__ ----- */


    //register user
    @Override
    public User registerUser(User user){
        jpaUserRepository.findById(user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User already exists!"));
        return userPersistenceMapper.toDomainModel(jpaUserRepository.save(userPersistenceMapper.toEntity(user)));
    }

}
