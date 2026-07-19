package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;

public class WalletUseCaseImpl implements WalletUseCase {

    //inject required dependencies
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    public WalletUseCaseImpl(
            WalletRepository walletRepository,
            UserRepository userRepository
    ) {
        this.walletRepository=walletRepository;
        this.userRepository=userRepository;
    }

    //get wallet balance
    @Override
    public Wallet getWalletBalance(String email){
        return walletRepository.findByUserEmail(email)
                .orElseThrow(() ->  new RuntimeException("wallet not found"));
    }

    @Override
    public Wallet depositMoney(String email, double amount) {
      if (amount < 0) {
          throw new IllegalArgumentException("Deposit amount must be greater than 0");
      }

      Wallet userWallet = walletRepository.findByUserEmail(email)
              .orElseThrow(() ->  new RuntimeException("users' wallet not found" + "," + email));

    }
}
