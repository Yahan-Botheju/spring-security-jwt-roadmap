package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records;

public record AuthenticatedUser(
        String accessToken,
        String email,
        String role
) {
}
