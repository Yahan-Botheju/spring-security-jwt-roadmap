package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records;

public record RegisterUserResult(
        Long userId,
        String email,
        String userRole,
        Long walletId,
        Double walletBalance
) {
}
