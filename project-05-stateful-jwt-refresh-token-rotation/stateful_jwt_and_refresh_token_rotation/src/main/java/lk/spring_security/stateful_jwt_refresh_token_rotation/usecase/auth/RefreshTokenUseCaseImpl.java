package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.abstract_helper.GenerateRefreshTokenSupport;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RefreshTokenResult;


public class RefreshTokenUseCaseImpl extends GenerateRefreshTokenSupport implements RefreshTokenUseCase {

    //inject required dependencies
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;


    public RefreshTokenUseCaseImpl(
            RefreshTokenRepository refreshTokenRepository,
            TokenService tokenService
    ) {
        super(refreshTokenRepository);
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenService = tokenService;
    }


    /*  __PUBLIC_METHODS__  */

    //create refresh token
    @Override
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

        String newAccessToken = tokenService.generateAccessToken(user);
        String newRefreshToken = tokenService.generateRefreshToken(user);

        //use private method
        generateRefreshToken(user, newRefreshToken);


        return new RefreshTokenResult(
                newAccessToken,
                newRefreshToken,
                user.getEmail(),
                user.getRole().name()
        );
    }
}
