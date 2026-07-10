package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.jpa;

import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
