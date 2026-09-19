package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RefreshTokenResult;

public interface RefreshTokenUseCase {

    //register user
    RefreshTokenResult refreshToken(String refreshToken);
}
