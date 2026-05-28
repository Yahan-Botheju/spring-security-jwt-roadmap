package lk.spring_security.method_level_security_global_security_exceptions.domain.repositories;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //user find by email/username
    Optional<User> userFindByEmail(String email);
}
