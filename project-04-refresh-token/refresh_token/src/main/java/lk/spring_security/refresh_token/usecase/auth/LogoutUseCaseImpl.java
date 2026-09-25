package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.usecase.auth.records.LogoutCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LogoutResult;

public class LogoutUseCaseImpl implements LogoutUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCaseImpl(
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //logout
    @Override
    public LogoutResult logout(LogoutCommand logoutCommand){

        String tokenStr = logoutCommand.refreshToken();

        //token availability
        if(tokenStr != null && !tokenStr.isBlank()){
            //delete token if available
            refreshTokenRepository.findByToken(tokenStr)
                    .ifPresent(refreshToken ->
                            refreshTokenRepository.deleteByToken(tokenStr));

        }
        return new LogoutResult(
                "User Logout Successful!!"
        );
    }
}
