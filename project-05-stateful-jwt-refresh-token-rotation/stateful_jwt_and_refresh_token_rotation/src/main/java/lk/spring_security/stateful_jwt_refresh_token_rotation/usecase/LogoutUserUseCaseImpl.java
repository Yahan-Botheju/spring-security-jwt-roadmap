package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;

public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUserUseCaseImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
}
