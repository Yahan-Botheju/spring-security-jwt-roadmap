package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RefreshTokenResult;

public interface RefreshTokenUseCase {

    //register user
    RefreshTokenResult refreshToken(String refreshToken);
}
