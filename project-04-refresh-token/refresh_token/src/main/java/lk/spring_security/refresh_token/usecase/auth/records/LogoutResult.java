package lk.spring_security.refresh_token.usecase.auth.records;

public record LogoutResult(
        String email,
        String message
) {
}
