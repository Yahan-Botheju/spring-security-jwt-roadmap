package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class WithdrawMoneyUseCaseImpl implements WithdrawMoneyUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;

    public WithdrawMoneyUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }
}
