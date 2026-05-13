package lk.spring_security.stateless_jwt.infrastructure.persistence;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.persistence.mappers.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceImpl implements UserRepository {

    //inject jpa repo
    private final JpaUserRepository jpaUserRepository;

    //inject persistence mapper
    private final UserPersistenceMapper userPersistenceMapper;
}
