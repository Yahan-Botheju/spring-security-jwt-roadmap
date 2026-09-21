package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._usecaseBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfigs {

    /* __AUTH_USE_CASES__ */

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

    /* __WALLET_USE_CASES__ */

    //wallet usecase impl
    @Bean
    public WalletBalanceUseCase walletBalanceUseCase(
            WalletRepository walletRepository
    ){
        return new WalletBalanceUseCaseImpl(walletRepository);
    }

    //deposit money usecase impl
    @Bean
    public DepositMoneyUseCase depositMoneyUseCase(WalletRepository walletRepository){
        return new DepositMoneyUseCaseImpl(walletRepository);
    }
}
