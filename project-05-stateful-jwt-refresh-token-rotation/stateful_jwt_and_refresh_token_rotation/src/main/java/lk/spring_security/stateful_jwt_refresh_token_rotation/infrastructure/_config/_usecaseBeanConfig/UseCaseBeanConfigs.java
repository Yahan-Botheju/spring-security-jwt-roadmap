package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._usecaseBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {

    //auth usecase impl
    @Bean
    public AuthUseCase authUseCase(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager,
            WalletRepository walletRepository
    ){
        return new AuthUseCaseImpl(
                refreshTokenRepository,
                userRepository,
                passwordEncoder,
                cookieService,
                tokenService,
                authenticationManager,
                walletRepository
        );
    }

    //wallet usecase impl
    @Bean
    public WalletUseCase walletUseCase(
            WalletRepository walletRepository,
            UserRepository userRepository
    ) {
        return new WalletUseCaseImpl(walletRepository, userRepository);
    }
}
