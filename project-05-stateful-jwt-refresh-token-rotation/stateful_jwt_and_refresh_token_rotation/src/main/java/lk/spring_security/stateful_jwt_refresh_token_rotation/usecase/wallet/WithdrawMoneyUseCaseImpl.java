package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WithdrawCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.WithdrawResult;

public class WithdrawMoneyUseCaseImpl implements WithdrawMoneyUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;

    public WithdrawMoneyUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    //withdraw money
    @Override
    public WithdrawResult withdrawMoney(WithdrawCommand withdrawCommand) {
        //validate incoming values
        if(withdrawCommand.email().isBlank() || withdrawCommand.amount() <= 0){
            throw new IllegalArgumentException("Cannot withdraw amount less or zero amount.");
        }

        //find user related to wallet
        Wallet userWallet = walletRepository.findByUserEmail(withdrawCommand.email())
                .orElseThrow(() ->  new RuntimeException("users' wallet not found" + "," + withdrawCommand.email()));

        //calculate new balance and update
        Double newBalance = userWallet.getWalletBalance() - withdrawCommand.amount();
        userWallet.depositMoney(newBalance);

        walletRepository.saveWallet(userWallet);

        return new WithdrawResult(
                userWallet.getUser().getEmail(),
                userWallet.getWalletBalance()
        );
    }
}
