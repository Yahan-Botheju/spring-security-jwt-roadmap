package lk.spring_security.refresh_token.domain.repositories;

import lk.spring_security.refresh_token.domain.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    //check token availability of db
    Optional<RefreshToken> findByToken(String token);
}
