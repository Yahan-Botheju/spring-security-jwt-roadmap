package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;

public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUserUseCaseImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //create logout user
    @Override
    public void logout(String refreshToken) {
        //toke validation
        if(refreshToken == null || refreshToken.isBlank()){
            return;
        }
        //get token
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() ->  new RuntimeException("Refresh token not found"));
        //use domain model
        token.markAsRevoked();
        //save as use one
        refreshTokenRepository.saveRefreshToken(token);
    }
}
