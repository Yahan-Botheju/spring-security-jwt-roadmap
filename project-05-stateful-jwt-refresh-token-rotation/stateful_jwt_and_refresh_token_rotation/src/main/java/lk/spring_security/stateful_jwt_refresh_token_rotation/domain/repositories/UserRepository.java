package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //find user by email
    Optional<User> findByEmail(String email);


}
