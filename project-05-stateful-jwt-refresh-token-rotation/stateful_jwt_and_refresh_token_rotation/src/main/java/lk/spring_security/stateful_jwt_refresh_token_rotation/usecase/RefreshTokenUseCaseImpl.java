package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;

public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenUseCaseImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
}
