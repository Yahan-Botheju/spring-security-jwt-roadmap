package lk.spring_security.method_level_security_global_security_exceptions.domain.repositories;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //user find by email/username
    Optional<User> findByEmail(String email);

    //user find by ID
    Optional<User> findById(Long userId);

    //save user (REGISTER USER)
    User saveUser(User user);

    //update user
    User updateUser(User user);

    //delete user
    void deleteUser(User user);
}
