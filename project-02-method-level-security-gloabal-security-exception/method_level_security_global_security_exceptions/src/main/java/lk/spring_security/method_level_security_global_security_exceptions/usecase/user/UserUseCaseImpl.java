package lk.spring_security.method_level_security_global_security_exceptions.usecase.user;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper.UserPersistenceMapper;

public class UserUseCaseImpl implements UserUseCase {

    //inject user repo
    private final UserRepository userRepository;

    //inject user persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;

    //create constructor
    public UserUseCaseImpl(
            UserRepository userRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        this.userRepository = userRepository;
        this.userPersistenceMapper = userPersistenceMapper;
    }
}
