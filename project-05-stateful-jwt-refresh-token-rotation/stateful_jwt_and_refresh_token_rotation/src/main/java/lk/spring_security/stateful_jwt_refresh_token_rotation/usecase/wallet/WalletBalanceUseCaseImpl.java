package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.abstract_helper.UserFindSupport;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WalletBalanceResult;

public class WalletBalanceUseCaseImpl extends UserFindSupport implements WalletBalanceUseCase {

    //inject required dependencies
    public WalletBalanceUseCaseImpl(WalletRepository walletRepository) {
        super(walletRepository);
    }


    //get wallet balance
    @Override
    public WalletBalanceResult getWalletBalance(WalletBalanceCommand walletBalanceCommand) {

        //use abstract class
         Wallet getWallet =  findUserWalletByEmail(walletBalanceCommand.email());

         return new  WalletBalanceResult(
                 getWallet.getUser().getEmail(),
                 getWallet.getWalletBalance()
         );

    }
}
