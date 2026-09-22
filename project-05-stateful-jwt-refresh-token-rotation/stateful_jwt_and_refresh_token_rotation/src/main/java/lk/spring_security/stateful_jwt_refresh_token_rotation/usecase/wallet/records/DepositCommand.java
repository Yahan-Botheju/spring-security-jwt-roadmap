package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.wallet.records;

public record DepositCommand(
        String email,
        double amount
) {
}
