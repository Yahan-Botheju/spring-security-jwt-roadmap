package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.LogoutUserCommand;

public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUserUseCaseImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //create logout user
    @Override
    public void logout(LogoutUserCommand logoutUserCommand) {
        //toke validation
        if(logoutUserCommand.refreshToken() == null || logoutUserCommand.refreshToken().isBlank()){
            return;
        }
        //get token
        RefreshToken token = refreshTokenRepository.findByToken(logoutUserCommand.refreshToken())
                .orElseThrow(() ->  new RuntimeException("Refresh token not found"));
        //use domain model
        token.markAsUsed();
        //save as use one
        refreshTokenRepository.saveRefreshToken(token);
    }
}
