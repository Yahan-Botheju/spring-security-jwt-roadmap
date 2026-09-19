package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RefreshTokenCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RefreshTokenResult;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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

    /*  __HELPER_METHODS__  */

    //generate new refresh token and save in db
    private void generateNewRefreshToken(User user, String refreshToken){
        RefreshToken newRefreshToken = RefreshToken.builder()
                .token(refreshToken)
                .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                .isUsed(false)
                .isRevoked(false)
                .user(user)
                .build();
        refreshTokenRepository.saveRefreshToken(newRefreshToken);
    }





        public RefreshTokenResult refreshToken(String refreshToken) {

        //get token from db
        RefreshToken storedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new IllegalStateException("Invalid refresh token"));

        //get user related to token
        User user = storedToken.getUser();

        //if used token is represented user all tokens are remove from db
        if(storedToken.isUsed()){
           refreshTokenRepository.revokeAllUserTokens(user.getUserId());
           throw new IllegalStateException("Refresh token has been revoked!! Please login again");
        }

        /* __TOKEN_ROTATION_PATH__ */
        //marking old token is used
        storedToken.markAsUsed();
        refreshTokenRepository.saveRefreshToken(storedToken);



        //use private method
        generateNewRefreshToken(user, refreshToken);


        return null;
    }
}
