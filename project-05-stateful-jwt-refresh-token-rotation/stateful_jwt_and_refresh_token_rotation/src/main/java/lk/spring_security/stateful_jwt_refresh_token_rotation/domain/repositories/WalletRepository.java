package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;

public interface WalletRepository {

    //save wallet
    Wallet saveWallet(Wallet wallet);
}
