package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records;

public record DepositResult(
        String email,
        Double walletBalance
) {
}
