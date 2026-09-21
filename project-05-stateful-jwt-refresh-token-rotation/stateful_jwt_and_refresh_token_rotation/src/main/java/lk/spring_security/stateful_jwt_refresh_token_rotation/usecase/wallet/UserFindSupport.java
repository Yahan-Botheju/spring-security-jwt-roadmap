package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public abstract class UserFindSupport {

    //inject required dependencies
    private final WalletRepository walletRepository;

    protected UserFindSupport(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    //find wallet related to user
    protected Wallet findUserWalletByEmail(String email) {
        return walletRepository.findByUserEmail(email)
                .orElseThrow(() ->  new ResourceNotFoundException("wallet not found"));
    }
}
