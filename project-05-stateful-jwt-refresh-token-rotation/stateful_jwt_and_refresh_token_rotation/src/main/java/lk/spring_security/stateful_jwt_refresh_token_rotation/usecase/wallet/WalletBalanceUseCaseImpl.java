package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceResult;

public class WalletBalanceUseCaseImpl implements WalletBalanceUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;

    public WalletBalanceUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    //get wallet balance
    @Override
    public WalletBalanceResult getWalletBalance(WalletBalanceCommand walletBalanceCommand) {

         Wallet getWallet =  walletRepository.findByUserEmail(walletBalanceCommand.email())
                .orElseThrow(() ->  new RuntimeException("wallet not found"));


         return new  WalletBalanceResult(
                 getWallet.getUser().getEmail(),
                 getWallet.getWalletBalance()
         );

    }
}
