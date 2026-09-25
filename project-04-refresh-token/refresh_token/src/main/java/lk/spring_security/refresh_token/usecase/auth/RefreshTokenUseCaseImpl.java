package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.domain.models.User;
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
    //refresh token
    @Override
    public RefreshTokenResult refreshToken(RefreshTokenCommand refreshTokenCommand){

        String refreshTokenStr = refreshTokenCommand.refreshToken();

        if (refreshTokenStr == null || refreshTokenStr.isBlank()) {
            throw new IllegalStateException("refreshTokenStr is null or empty");
        }

        //get refresh token from db if not throw an error
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenStr)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        //check token is expired through DOMAIN MODEL
        if(refreshToken.isExpired()){
            //remove from db
            refreshTokenRepository.deleteByToken(refreshToken.getToken());
            throw new IllegalStateException("refreshToken is expired");
        }

        //get user from token and create new access token
        User newUser = refreshToken.getUser();
        String newAccessToken = tokenService.generateToken(newUser);

        return new RefreshTokenResult(
                newUser.getEmail(),
                newAccessToken
        );
    }
}
