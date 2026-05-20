package lk.spring_security.stateless_jwt.infrastructure.user.persistence;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserPersistenceImpl implements UserRepository {

    //inject jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;

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
}
