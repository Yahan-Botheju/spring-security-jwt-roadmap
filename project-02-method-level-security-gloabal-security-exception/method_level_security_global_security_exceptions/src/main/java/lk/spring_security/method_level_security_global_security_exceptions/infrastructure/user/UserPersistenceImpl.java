package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper.UserPersistenceMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserPersistenceImpl implements UserRepository {

    //inject user jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;


    public UserPersistenceImpl(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }

    /* ----- HELPER METHODS ----- */

    @Override
    public Optional<User> userFindByEmail(String email) {
        return jpaUserRepository.userFindByEmail(email).map(userPersistenceMapper::toDomainModel);
    }

}
