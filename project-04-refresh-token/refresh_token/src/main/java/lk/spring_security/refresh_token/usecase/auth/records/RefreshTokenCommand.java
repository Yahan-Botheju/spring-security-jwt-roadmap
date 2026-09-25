package lk.spring_security.refresh_token.usecase.auth.records;

public record RefreshTokenCommand(
        String refreshToken
) {
}
