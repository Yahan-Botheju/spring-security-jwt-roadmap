package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;


import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;

public abstract class GenerateRefreshTokenSupport {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    protected GenerateRefreshTokenSupport(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //generate refresh token and save
    protected void generateRefreshToken(User user, String refreshTokenResult) {
        RefreshToken newRefreshToken = RefreshToken.createNewRefreshToken(user, refreshTokenResult);
        refreshTokenRepository.saveRefreshToken(newRefreshToken);
    }
}
