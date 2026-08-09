package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class WalletUseCaseImpl implements WalletUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;


    public WalletUseCaseImpl(
            WalletRepository walletRepository
    ) {
        this.walletRepository=walletRepository;
    }

    //get wallet balance
    @Override
    public Wallet getWalletBalance(String email){
        return walletRepository.findByUserEmail(email)
                .orElseThrow(() ->  new RuntimeException("wallet not found"));
    }

    @Override
    public Wallet depositMoney(String email, double amount) {
        //check amount
      if (amount < 0) {
          throw new IllegalArgumentException("Deposit amount must be greater than 0");
      }

      //find user related to wallet
      Wallet userWallet = walletRepository.findByUserEmail(email)
              .orElseThrow(() ->  new RuntimeException("users' wallet not found" + "," + email));

      //set new balance
      Double newBalance = userWallet.getWalletBalance() + amount;
      userWallet.setWalletBalance(newBalance);

      return walletRepository.saveWallet(userWallet);
    }

    //withdraw money
    @Override
    public Wallet withdrawMoney(String email, double amount) {
        //check amount
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }

        //find user related to wallet
        Wallet userWallet = walletRepository.findByUserEmail(email)
                .orElseThrow(() ->  new RuntimeException("users' wallet not found" + "," + email));

        //calculate new balance and update
        Double newBalance = userWallet.getWalletBalance() - amount;
        userWallet.setWalletBalance(newBalance);

        return walletRepository.saveWallet(userWallet);
    }
}
