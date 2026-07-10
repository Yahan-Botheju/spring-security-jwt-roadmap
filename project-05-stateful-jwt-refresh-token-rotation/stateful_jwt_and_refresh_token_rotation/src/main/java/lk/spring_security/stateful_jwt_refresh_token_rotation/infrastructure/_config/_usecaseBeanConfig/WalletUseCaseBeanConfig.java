package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._usecaseBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.WalletUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletUseCaseBeanConfig {
    @Bean
    public WalletUseCase walletUseCase(
            WalletRepository walletRepository,
            UserRepository userRepository
    ) {
        return new WalletUseCaseImpl(walletRepository, userRepository);
    }
}
