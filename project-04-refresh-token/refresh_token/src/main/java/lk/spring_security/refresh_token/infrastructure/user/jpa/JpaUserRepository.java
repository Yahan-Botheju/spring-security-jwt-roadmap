package lk.spring_security.refresh_token.infrastructure.user.jpa;

import lk.spring_security.refresh_token.infrastructure.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
