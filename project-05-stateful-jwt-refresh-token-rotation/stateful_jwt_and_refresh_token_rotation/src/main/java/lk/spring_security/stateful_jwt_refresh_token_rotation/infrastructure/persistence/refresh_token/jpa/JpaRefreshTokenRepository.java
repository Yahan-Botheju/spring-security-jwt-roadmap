package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.jpa;

import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Long> {

    Optional<RefreshTokenEntity> findByToken(String token);
}
