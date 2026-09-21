package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models;


public class Wallet {
    private Long walletId;
    private Double walletBalance;

    private User user;

    public Wallet(Long walletId, Double walletBalance, User user) {
        this.walletId = walletId;
        this.walletBalance = walletBalance;
        this.user = user;
    }

    public Long getWalletId() {    return walletId;
    }

    public Double getWalletBalance() {    return walletBalance;
    }

    public User getUser() {    return user;
    }

    /* __FACTORY_METHOD__ */

    public static Wallet createNewWallet(
            User user
    ){
        return new Wallet(null,1000.0, user);
    }

    /* __DOMAIN_LOGIC__ */
    public void depositMoney(Double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.walletBalance += amount;
    }
}
