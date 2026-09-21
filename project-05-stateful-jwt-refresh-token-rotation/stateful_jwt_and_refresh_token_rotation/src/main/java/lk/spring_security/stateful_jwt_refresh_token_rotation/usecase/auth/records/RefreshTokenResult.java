package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records;


public record RefreshTokenResult(
        String newAccessToken,
        String newRefreshToken,
        String email,
        String role
) {
}
