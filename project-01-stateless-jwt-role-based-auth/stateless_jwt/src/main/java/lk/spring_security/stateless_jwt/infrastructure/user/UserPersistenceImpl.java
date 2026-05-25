package lk.spring_security.stateless_jwt.infrastructure.user;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers.UserPersistenceMapper;

import java.util.Optional;

public class UserPersistenceImpl implements UserRepository {

    //inject jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;

    public UserPersistenceImpl(JpaUserRepository jpaUserRepository, UserPersistenceMapper userPersistenceMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    /* ----- HELPER METHODS ----- */

    //user find by email
    public Optional<User> userFindByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(userPersistenceMapper::toDomainModel);
    }

    //save new user
    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userPersistenceMapper.toEntity(user);
        jpaUserRepository.save(userEntity);
        return userPersistenceMapper.toDomainModel(userEntity);
    }

    //update user
    @Override
    public User updateUser(User user) {
        UserEntity userEntity = jpaUserRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserEntity toEntity =  userPersistenceMapper.updateUser(user, userEntity);
        jpaUserRepository.save(toEntity);
        return userPersistenceMapper.toDomainModel(toEntity);
    }

    //delete user
    @Override
    public void deleteUser(User user){
        UserEntity existingUser = jpaUserRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        jpaUserRepository.delete(existingUser);
    }
}
