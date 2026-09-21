package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class WalletBalanceUseCaseImpl implements WalletBalanceUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;

    public WalletBalanceUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }
}
