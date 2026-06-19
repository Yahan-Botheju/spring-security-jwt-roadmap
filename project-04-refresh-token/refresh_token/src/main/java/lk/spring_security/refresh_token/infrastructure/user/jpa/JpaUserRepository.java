package lk.spring_security.refresh_token.infrastructure.user.jpa;

import lk.spring_security.refresh_token.infrastructure.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    //user find by email
    Optional<UserEntity> findByEmail(String email);
}
