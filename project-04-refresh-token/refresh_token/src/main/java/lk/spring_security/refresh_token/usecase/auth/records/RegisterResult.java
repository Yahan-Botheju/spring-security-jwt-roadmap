package lk.spring_security.refresh_token.usecase.auth.records;

public record RegisterResult(
        Long userId,
        String status,
        String message
) {
}
