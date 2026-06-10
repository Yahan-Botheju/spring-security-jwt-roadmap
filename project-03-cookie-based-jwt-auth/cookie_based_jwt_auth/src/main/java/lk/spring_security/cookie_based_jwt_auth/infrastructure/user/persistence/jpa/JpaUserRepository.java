package lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.jpa;

import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    //create custom query for find user by username
    Optional<UserEntity> findByEmail(String email);
}
