package lk.spring_security.refresh_token.domain.repositories;

import lk.spring_security.refresh_token.domain.models.User;

import java.util.Optional;

public interface UserRepository {

    //user find by email
    Optional<User> findByEmail(String email);

    //user find by ID
    Optional<User> findById(Long userId);
}
