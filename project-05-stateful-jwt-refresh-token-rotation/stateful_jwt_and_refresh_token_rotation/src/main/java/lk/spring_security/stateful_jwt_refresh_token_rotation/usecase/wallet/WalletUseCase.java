package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;

public interface WalletUseCase {

    //get wallet balance
    Wallet getWalletBalance(String email);

    //get deposit amount
    Wallet depositMoney(String email, double amount);
}
