package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._usecaseBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfigs {

    /* __AUTH_USECASES__ */

    //refresh token usecase impl
    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(
            RefreshTokenRepository refreshTokenRepository,
            TokenService tokenService
    ) {
        return new RefreshTokenUseCaseImpl(refreshTokenRepository, tokenService);
    }

    //register usecase impl
    @Bean
    public RegisterUserUseCase registerUserUseCase(
            UserRepository userRepository,
            IdentityProvider identityProvider,
            WalletRepository walletRepository
    ) {
        return new RegisterUserUseCaseImpl(userRepository, identityProvider, walletRepository);
    }

    //login user usecase impl
    @Bean
    public LoginUserUseCase loginUserUseCase(
            UserRepository userRepository,
            TokenService tokenService,
            IdentityProvider identityProvider,
            RefreshTokenRepository refreshTokenRepository
    ) {
        return new LoginUserUseCaseImpl(userRepository, tokenService, identityProvider, refreshTokenRepository);
    }

    //logout user usecase impl
    @Bean
    public LogoutUserUseCase logoutUserUseCase(
            RefreshTokenRepository refreshTokenRepository
    ){
        return new LogoutUserUseCaseImpl(refreshTokenRepository);
    }
}
