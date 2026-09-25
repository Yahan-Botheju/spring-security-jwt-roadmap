package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.TokenService;

public class RefreshTokenUseCaseImpl {

    //inject required dependencies
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final TokenService tokenService;

    public RefreshTokenUseCaseImpl(
            RefreshTokenUseCase refreshTokenUseCase,
            TokenService tokenService
    ) {
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.tokenService = tokenService;
    }
}
