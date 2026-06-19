package lk.spring_security.refresh_token.infrastructure.user.jpa;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.infrastructure.user.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Long> {
}
