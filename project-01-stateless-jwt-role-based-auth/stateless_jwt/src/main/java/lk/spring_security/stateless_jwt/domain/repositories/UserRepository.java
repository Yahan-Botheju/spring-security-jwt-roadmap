package lk.spring_security.stateless_jwt.domain.repositories;

import lk.spring_security.stateless_jwt.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //user find by email
    Optional<User> userFindByEmail(String email);

    //create new user
    User saveUser(User user);
}
