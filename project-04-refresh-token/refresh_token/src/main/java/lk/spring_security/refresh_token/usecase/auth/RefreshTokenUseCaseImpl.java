package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.usecase.auth.records.RefreshTokenCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RefreshTokenResult;

public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;

    public RefreshTokenUseCaseImpl(
            RefreshTokenRepository refreshTokenRepository,
            TokenService tokenService
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenService = tokenService;
    }

    public RefreshTokenResult refreshToken(RefreshTokenCommand refreshTokenCommand) {

        String refreshTokenStr = refreshTokenCommand.refreshToken();

        if (refreshTokenStr == null || refreshTokenStr.isBlank()) {
            throw new IllegalStateException("refreshTokenStr is null or empty");
        }

        //get refresh token from db if not throw an error
        RefreshToken existingRefreshToken = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

    }
}
