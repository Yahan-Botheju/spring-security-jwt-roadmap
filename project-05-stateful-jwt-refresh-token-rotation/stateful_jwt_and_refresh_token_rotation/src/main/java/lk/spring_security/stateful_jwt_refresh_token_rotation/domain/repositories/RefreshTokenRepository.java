package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;

public interface RefreshTokenRepository {

    //save token
    RefreshToken saveRefreshToken(RefreshToken refreshToken);
}
