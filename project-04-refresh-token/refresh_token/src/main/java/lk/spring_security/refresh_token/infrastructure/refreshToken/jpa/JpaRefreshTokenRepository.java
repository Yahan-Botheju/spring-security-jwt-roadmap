package lk.spring_security.refresh_token.infrastructure.refreshToken.jpa;

import lk.spring_security.refresh_token.infrastructure.refreshToken.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity,Long> {
    //user find by token
    Optional<RefreshTokenEntity> findByToken(String token);

    //delete token in user logout
    void deleteByUserEmail(String email);
}
