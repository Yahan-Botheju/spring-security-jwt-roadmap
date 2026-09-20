package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._usecaseBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfigs {

    //auth usecase impl
    @Bean
    public AuthUseCase authUseCase(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            IdentityProvider identityProvider,
            CookieService cookieService,
            TokenService tokenService,
            WalletRepository walletRepository
    ) {
        return new AuthUseCaseImpl(
                refreshTokenRepository,
                userRepository,
                identityProvider,
                cookieService,
                tokenService,
                walletRepository
        );
    }

    //wallet usecase impl
    @Bean
    public WalletUseCase walletUseCase(
            WalletRepository walletRepository
    ) {
        return new WalletUseCaseImpl(walletRepository);
    }


    /* _REF__ */


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
