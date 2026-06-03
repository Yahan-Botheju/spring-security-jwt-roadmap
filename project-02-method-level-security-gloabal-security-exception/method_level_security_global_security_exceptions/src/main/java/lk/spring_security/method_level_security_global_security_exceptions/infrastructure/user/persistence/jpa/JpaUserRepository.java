package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa;

import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository  extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long userId);
}
