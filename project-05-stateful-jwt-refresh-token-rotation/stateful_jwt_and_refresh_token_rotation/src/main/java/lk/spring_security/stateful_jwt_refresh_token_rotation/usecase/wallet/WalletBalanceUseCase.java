package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;


import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceResult;

public interface WalletBalanceUseCase {

    //get wallet balance
    WalletBalanceResult getWalletBalance(WalletBalanceCommand walletBalanceCommand);
}
