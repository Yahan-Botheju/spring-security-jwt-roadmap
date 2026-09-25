package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.usecase.auth.records.RefreshTokenCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RefreshTokenResult;

public interface RefreshTokenUseCase {
    //refresh token
    RefreshTokenResult refreshToken(RefreshTokenCommand refreshTokenCommand);
}
