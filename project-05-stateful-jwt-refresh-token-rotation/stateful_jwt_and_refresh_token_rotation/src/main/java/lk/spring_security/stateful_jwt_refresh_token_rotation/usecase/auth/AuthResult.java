package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

public record AuthResult(
        String accessToken,
        String email,
        String role
) {
}
