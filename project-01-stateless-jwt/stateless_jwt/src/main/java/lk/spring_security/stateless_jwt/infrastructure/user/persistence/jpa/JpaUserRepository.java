package lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa;

import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
