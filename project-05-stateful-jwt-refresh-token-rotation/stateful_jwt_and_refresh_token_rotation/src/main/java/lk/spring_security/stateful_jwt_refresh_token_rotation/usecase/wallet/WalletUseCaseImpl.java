package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class WalletUseCaseImpl implements WalletUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    public WalletUseCaseImpl(
            WalletRepository walletRepository,
            UserRepository userRepository
    ) {
        this.walletRepository=walletRepository;
        this.userRepository=userRepository;
    }
}
