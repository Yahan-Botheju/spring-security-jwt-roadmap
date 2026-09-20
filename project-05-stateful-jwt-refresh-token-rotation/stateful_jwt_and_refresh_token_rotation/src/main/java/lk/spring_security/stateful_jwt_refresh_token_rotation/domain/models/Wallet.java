package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;

import lombok.*;

@Getter
public class Wallet {
    private Long walletId;
    private Double walletBalance;

    private User user;

    public Wallet(Long walletId, Double walletBalance, User user) {
        this.walletId = walletId;
        this.walletBalance = walletBalance;
        this.user = user;
    }

    /* __FACTORY_METHOD__ */

    public static Wallet createNewWallet(
            User user
    ){
        return new Wallet(null,1000.0, user);
    }
}
