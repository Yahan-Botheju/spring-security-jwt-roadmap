package lk.spring_security.cookie_based_jwt_auth.domain.repositories;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //user find by email
    Optional<User> userFindByEmail(String email);
}
