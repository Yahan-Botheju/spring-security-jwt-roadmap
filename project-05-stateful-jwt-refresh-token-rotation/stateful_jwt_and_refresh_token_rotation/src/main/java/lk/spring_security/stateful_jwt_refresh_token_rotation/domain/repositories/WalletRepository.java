package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface WalletRepository {

    //save wallet
    Wallet saveWallet(Wallet wallet);

    //wallet find by email
    Optional<Wallet> findByUserEmail(String email);
}
