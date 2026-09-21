package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public abstract class UserFindSupport {

    //inject required dependencies
    private final WalletRepository walletRepository;

    protected UserFindSupport(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


}
