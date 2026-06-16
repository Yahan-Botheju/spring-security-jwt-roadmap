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

    //user find by ID
    @Override
    public Optional<User> userFindById(Long userId){
        return jpaUserRepository.findById(userId).map(userPersistenceMapper::toDomainModel);
    }


    /* ----- __PUBLIC_METHODS__ ----- */


    //register user
    @Override
    public User registerUser(User user){
        if(userFindByEmail(user.getEmail()).isPresent()){
            throw  new IllegalArgumentException("User already exists");
        }
        return userPersistenceMapper.toDomainModel(jpaUserRepository.save(userPersistenceMapper.toEntity(user)));
    }

    //update user
    @Override
    public User updateUser(User user){
        UserEntity userExistence = jpaUserRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User already exists"));
        UserEntity updatedUserEntity = userPersistenceMapper.updateEntity(user, userExistence);
        UserEntity savedUserEntity = jpaUserRepository.save(updatedUserEntity);
        return userPersistenceMapper.toDomainModel(savedUserEntity);
    }

    //delete user
    @Override
    public void deleteUser(Long userId){
        UserEntity existingUserEntity = jpaUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        jpaUserRepository.delete(existingUserEntity);
    }
}
