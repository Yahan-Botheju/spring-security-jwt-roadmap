package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class RegisterUserUseCaseImpl {

    //inject required dependencies
    private final UserRepository userRepository;
    private final IdentityProvider identityProvider;
    private final WalletRepository walletRepository;

    public RegisterUserUseCaseImpl(
            UserRepository userRepository,
            IdentityProvider identityProvider,
            WalletRepository walletRepository
    ) {
        this.userRepository = userRepository;
        this.identityProvider = identityProvider;
        this.walletRepository = walletRepository;
    }
}
