package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.DepositCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records.DepositResult;

public class DepositMoneyUseCaseImpl implements DepositMoneyUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;

    public DepositMoneyUseCaseImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    //deposit money
    @Override
    public DepositResult depositMoney(DepositCommand depositCommand) {
        //validate incoming fields
        if(depositCommand.email().isBlank() || depositCommand.amount() <= 0){
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }

        //find user related to wallet
        Wallet userWallet = walletRepository.findByUserEmail(depositCommand.email())
                .orElseThrow(() ->  new RuntimeException("users' wallet not found" + "," + depositCommand.email()));


        //call domain model for set mutate account
        userWallet.depositMoney(depositCommand.amount());

        return new DepositResult(
                userWallet.getUser().getEmail(),
                userWallet.getWalletBalance()
        );
    }
}
