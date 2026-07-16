package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    //find token
    Optional<RefreshToken> findByToken(String token);

    //save token
    RefreshToken saveRefreshToken(RefreshToken refreshToken);


}
